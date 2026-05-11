package com.minhdk.wefashion.presentation.ui.screen.cart.coupon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.CouponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CouponViewModel @Inject constructor(
    private val couponRepo: CouponRepository,
    private val accountRepo: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CouponState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadCoupons()
    }

    private fun loadCoupons() {
        val userId = accountRepo.getCurrentAccount()?.id
        if (userId == null) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Vui lòng đăng nhập") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = couponRepo.getCouponsByUser(userId)) {
                is RequestResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, coupons = result.data.coupons)
                    }
                }
                is RequestResult.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Không thể tải coupon")
                    }
                }
            }
        }
    }
}

data class CouponState(
    val isLoading: Boolean = false,
    val coupons: List<DtoCoupon> = emptyList(),
    val errorMessage: String? = null
)

