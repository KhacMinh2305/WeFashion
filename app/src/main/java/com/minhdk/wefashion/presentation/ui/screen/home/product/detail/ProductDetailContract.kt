package com.minhdk.wefashion.presentation.ui.screen.home.product.detail

import com.minhdk.wefashion.domain.data.product.DtoProductDetail

data class ProductDetailState(
    val isLoading: Boolean = false,
    val productDetail: DtoProductDetail? = null,
    val quantity: Int = 1,
    val errorMessage: String? = null
)

sealed class ProductDetailIntent {
    data object IncreaseQuantity : ProductDetailIntent()
    data object DecreaseQuantity : ProductDetailIntent()
    data object AddToCart : ProductDetailIntent()
}

sealed class ProductDetailEffect
