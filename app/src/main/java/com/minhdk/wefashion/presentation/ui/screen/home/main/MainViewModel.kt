package com.minhdk.wefashion.presentation.ui.screen.home.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.CouponRepository
import com.minhdk.wefashion.domain.repository.ProductRepository
import com.minhdk.wefashion.domain.repository.UserRepository
import com.minhdk.wefashion.presentation.ui.common.UiState
import com.minhdk.wefashion.util.helper.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepo: AccountRepository,
    private val userRpo: UserRepository,
    private val productRepo: ProductRepository,
    private val couponRepo: CouponRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onIntent(MainIntent.Load)
    }

    fun onIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.Load -> {
                loadUser()
                loadCoupons()
                loadTopRated()
                loadBestSeller()
                loadMostLiked()
            }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            _uiState.update { it.copy(user = UiState.Loading) }
            accountRepo.getCurrentAccount()?.let { acc ->
                when (val result = userRpo.getCachedUser()) {
                    is RequestResult.Success -> {
                        _uiState.update { it.copy(user = UiState.Success(result.data)) }
                    }
                    is RequestResult.Error -> {
                        _uiState.update { it.copy(user = UiState.Error(result.error)) }
                    }
                }
            }
        }
    }

    private fun loadCoupons() {
        viewModelScope.launch {
            _uiState.update { it.copy(coupons = UiState.Loading) }
            when (val result = couponRepo.getCoupons()) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(coupons = UiState.Success(result.data)) }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(coupons = UiState.Error(result.error)) }
                }
            }
        }
    }

    private fun loadTopRated() {
        viewModelScope.launch {
            _uiState.update { it.copy(topRated = UiState.Loading) }
            when (val result = productRepo.getTopRatedProducts(10, 0)) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(topRated = UiState.Success(result.data)) }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(topRated = UiState.Error(result.error)) }
                }
            }
        }
    }

    private fun loadBestSeller() {
        viewModelScope.launch {
            _uiState.update { it.copy(bestSeller = UiState.Loading) }
            when (val result = productRepo.getBestSellerProducts(10, 0)) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(bestSeller = UiState.Success(result.data)) }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(bestSeller = UiState.Error(result.error)) }
                }
            }
        }
    }

    private fun loadMostLiked() {
        viewModelScope.launch {
            _uiState.update { it.copy(mostLiked = UiState.Loading) }
            when (val result = productRepo.getMostLikedProducts(10, 0)) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(mostLiked = UiState.Success(result.data)) }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(mostLiked = UiState.Error(result.error)) }
                }
            }
        }
    }
}
