package com.minhdk.wefashion.presentation.ui.screen.cart.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.CartRepository
import com.minhdk.wefashion.domain.repository.CouponRepository
import com.minhdk.wefashion.domain.repository.OrderRepository
import com.minhdk.wefashion.domain.repository.UserRepository
import com.minhdk.wefashion.presentation.ui.navigation.Cart
import com.minhdk.wefashion.util.helper.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val accountRepo: AccountRepository,
    private val userRepo: UserRepository,
    private val cartRepo: CartRepository,
    private val couponRepo: CouponRepository,
    private val orderRepo: OrderRepository
) : ViewModel() {

    val route by lazy {
        return@lazy try {
            savedStateHandle.toRoute<Cart.Payment>()
        } catch (_: Exception) {
            null
        }
    }

    private val _uiState = MutableStateFlow(
        CartState(
            discount = route?.discount,
            shippingFee = route?.shippingFee,
            total = route?.orderTotal,
            isLoading = true
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<CartEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        loadCart()
    }

    fun onIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.LoadCart -> loadCart()
            is CartIntent.ToggleSelectSku -> toggleSelect(intent.sku)
            is CartIntent.UpdateSkuQuantity -> updateSkuQuantity(
                intent.sku,
                intent.change,
                intent.amount
            )

            is CartIntent.PromoCodeChanged -> {
                _uiState.update { it.copy(promoCode = intent.value) }
            }

            is CartIntent.ApplyPromo -> applyPromo()
            is CartIntent.Checkout -> checkout(intent.addressId)
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            val res = userRepo.getCachedUser()
            if (res is RequestResult.Error) {
                _effect.trySend(CartEffect.ShowToast("Vui lòng đăng nhập"))
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }
            val userId = (res as RequestResult.Success).data.id
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = cartRepo.getCart(userId)) {
                is RequestResult.Success -> {
                    val cart = result.data
                    val selected = cart?.items?.map { it.sku }?.toSet().orEmpty()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            cart = cart,
                            selectedSkus = selected
                        )
                    }
                }

                is RequestResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.error.message
                        )
                    }
                    _effect.trySend(CartEffect.ShowToast("Không thể tải giỏ hàng"))
                }
            }
        }
    }

    private fun toggleSelect(sku: Int) {
        _uiState.update { state ->
            val newSet = state.selectedSkus.toMutableSet()
            if (newSet.contains(sku)) newSet.remove(sku) else newSet.add(sku)
            state.copy(selectedSkus = newSet)
        }
    }

    private fun updateSkuQuantity(sku: Int, change: String, amount: Int) {
        val userId = accountRepo.getCurrentAccount()?.id ?: return
        val currentQty = _uiState.value.cart?.items?.firstOrNull { it.sku == sku }?.quantity ?: 1
        if (change == "minus" && currentQty <= 1) {
            viewModelScope.launch {
                when (cartRepo.deleteSku(userId, sku)) {
                    is RequestResult.Success -> {
                        _uiState.update { state ->
                            val cart = state.cart ?: return@update state
                            val items = cart.items.filterNot { it.sku == sku }
                            val selected = state.selectedSkus - sku
                            val discount = state.appliedCoupon?.let { calculateDiscount(it) } ?: 0
                            state.copy(
                                cart = cart.copy(items = items),
                                selectedSkus = selected,
                                discountValue = discount
                            )
                        }
                    }

                    is RequestResult.Error -> {
                        _effect.trySend(CartEffect.ShowToast("Xóa sản phẩm thất bại"))
                    }
                }
            }
            return
        }
        viewModelScope.launch {
            when (val result = cartRepo.updateSkuQuantity(userId, sku, change, amount)) {
                is RequestResult.Success -> {
                    val applied = result.data.applied
                    _uiState.update { state ->
                        val cart = state.cart ?: return@update state
                        val items = cart.items.map { item ->
                            if (item.sku == sku) {
                                val next =
                                    if (change == "plus") item.quantity + applied else item.quantity - applied
                                item.copy(quantity = next.coerceAtLeast(1))
                            } else {
                                item
                            }
                        }
                        state.copy(cart = cart.copy(items = items))
                    }
                }

                is RequestResult.Error -> {
                    _effect.trySend(CartEffect.ShowToast("Cập nhật số lượng thất bại"))
                }
            }
        }
    }

    private fun applyPromo() {
        val userId = accountRepo.getCurrentAccount()?.id
        if (userId == null) {
            _effect.trySend(CartEffect.ShowToast("Vui lòng đăng nhập"))
            return
        }

        val code = _uiState.value.promoCode.trim()
        if (code.isEmpty()) {
            _effect.trySend(CartEffect.ShowToast("Vui lòng nhập mã khuyến mãi"))
            return
        }

        viewModelScope.launch {
            when (val result = couponRepo.getCouponsByUser(userId)) {
                is RequestResult.Success -> {
                    val coupon =
                        result.data.coupons.firstOrNull { it.name?.equals(code, true) == true }
                    if (coupon == null) {
                        _effect.trySend(CartEffect.ShowToast("Mã không hợp lệ"))
                        _uiState.update { it.copy(appliedCoupon = null, discountValue = 0) }
                        return@launch
                    }

                    val discount = calculateDiscount(coupon)
                    _uiState.update { it.copy(appliedCoupon = coupon, discountValue = discount) }
                    _effect.trySend(CartEffect.ShowToast("Áp dụng mã thành công"))
                }

                is RequestResult.Error -> {
                    _effect.trySend(CartEffect.ShowToast("Không thể áp dụng mã"))
                }
            }
        }
    }

    private fun calculateDiscount(coupon: DtoCoupon): Int {
        val subtotal = _uiState.value.cart?.items.orEmpty().sumOf { it.price * it.quantity }
        if (subtotal <= 0 || subtotal < coupon.minOrderValue) return 0

        val discount = when (coupon.discountType?.trim()?.uppercase()) {
            "%" -> (subtotal * coupon.discountValue) / 100
            "VND" -> coupon.discountValue
            "PERCENT", "PERCENTAGE" -> (subtotal * coupon.discountValue) / 100
            else -> coupon.discountValue
        }
        return discount.coerceAtMost(coupon.maxDiscount)
    }

    private fun checkout(addressId: Int?) {
        if (route == null) return
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.value = _uiState.value.copy(isProcessingPayment = true)

            val showError: (String) -> Unit = {
                _uiState.value = _uiState.value.copy(isProcessingPayment = false)
                _effect.trySend(CartEffect.ShowToast(it))
            }

            try {
                val userId = (userRepo.getCachedUser() as RequestResult.Success).data.id
                with(_uiState.value) {
                    val result = orderRepo.createOrder(
                        userId,
                        discount = discount ?: return@launch,
                        shippingFee = shippingFee ?: return@launch,
                        total = total ?: return@launch,
                        userId = userId,
                        addressId = addressId ?: run {
                            showError("No address selected")
                            return@launch
                        },
                        cart = cart ?: return@launch
                    )
                    when(result) {
                        is RequestResult.Error -> {
                            showError(result.error.message ?: "Something went wrong")
                            return@launch
                        }
                        is RequestResult.Success -> {
                            _effect.trySend(CartEffect.StartPaymentProcess(result.data.checkoutUrl))
                        }
                    }
                }
            } catch (_: Exception) {
                showError("Something went wrong")
            }
        }
    }


}
