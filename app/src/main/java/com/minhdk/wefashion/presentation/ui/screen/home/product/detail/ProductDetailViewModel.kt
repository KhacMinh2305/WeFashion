package com.minhdk.wefashion.presentation.ui.screen.home.product.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.CartRepository
import com.minhdk.wefashion.domain.repository.ProductRepository
import com.minhdk.wefashion.presentation.ui.navigation.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandler: SavedStateHandle,
    private val productRepo: ProductRepository,
    private val cartRepo: CartRepository,
    private val accountRepo: AccountRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<ProductDetailEffect>()
    val effect = _effect.receiveAsFlow()

    private val productId by lazy {
        savedStateHandler.toRoute<Home.ProductDetail>().productId
    }

    init {
        loadProductDetail()
    }

    fun handle(intent: ProductDetailIntent) {
        when (intent) {
            ProductDetailIntent.IncreaseQuantity -> {
                _uiState.value = _uiState.value.copy(quantity = _uiState.value.quantity + 1)
            }
            ProductDetailIntent.DecreaseQuantity -> {
                val current = _uiState.value.quantity
                _uiState.value = _uiState.value.copy(quantity = (current - 1).coerceAtLeast(1))
            }
            is ProductDetailIntent.AddToCart -> {
                syncCartQuantity(intent.sku, intent.quantity)
            }
        }
    }

    private fun syncCartQuantity(sku: Int, quantity: Int) {
        val desiredQty = quantity.coerceAtLeast(1)
        val userId = accountRepo.getCurrentAccount()?.id
        if (userId == null) {
            _effect.trySend(ProductDetailEffect.ShowToast("Vui lòng đăng nhập"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val currentQty = when (val cartResult = cartRepo.getCart(userId)) {
                is RequestResult.Success -> cartResult.data?.items?.firstOrNull { it.sku == sku }?.quantity ?: 0
                is RequestResult.Error -> 0
            }

            val diff = desiredQty - currentQty
            if (diff == 0) {
                _effect.trySend(ProductDetailEffect.ShowToast("Số lượng đã được cập nhật"))
                return@launch
            }

            if (diff > 0) {
                when (val res = cartRepo.updateSkuQuantity(userId, sku, "plus", diff)) {
                    is RequestResult.Success -> {
                        _effect.trySend(ProductDetailEffect.ShowToast("Đã cập nhật giỏ hàng"))
                    }
                    is RequestResult.Error -> {
                        _effect.trySend(ProductDetailEffect.ShowToast("Không thể cập nhật giỏ hàng"))
                    }
                }
            } else {
                val minusAmount = (-diff).coerceAtMost((currentQty - 1).coerceAtLeast(0))
                if (minusAmount <= 0) {
                    _effect.trySend(ProductDetailEffect.ShowToast("Số lượng tối thiểu là 1"))
                    return@launch
                }
                when (val res = cartRepo.updateSkuQuantity(userId, sku, "minus", minusAmount)) {
                    is RequestResult.Success -> {
                        _effect.trySend(ProductDetailEffect.ShowToast("Đã cập nhật giỏ hàng"))
                    }
                    is RequestResult.Error -> {
                        _effect.trySend(ProductDetailEffect.ShowToast("Không thể cập nhật giỏ hàng"))
                    }
                }
            }
        }
    }

    private fun loadProductDetail() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = productRepo.getProductDetails(productId)) {
                is RequestResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        productDetail = result.data,
                        errorMessage = null
                    )
                }
                is RequestResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.error.message
                    )
                }
            }
        }
    }

}