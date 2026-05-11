package com.minhdk.wefashion.presentation.ui.screen.home.product.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.ProductRepository
import com.minhdk.wefashion.presentation.ui.navigation.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandler: SavedStateHandle,
    private val productRepo: ProductRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailState(isLoading = true))
    val uiState = _uiState.asStateFlow()

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
            ProductDetailIntent.AddToCart -> Unit
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