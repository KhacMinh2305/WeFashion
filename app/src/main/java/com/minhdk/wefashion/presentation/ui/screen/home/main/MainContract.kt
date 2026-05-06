package com.minhdk.wefashion.presentation.ui.screen.home.main

import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.data.product.DtoProduct
import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.presentation.ui.common.UiState

data class MainUiState(
    val user: UiState<DtoUser> = UiState.Loading,
    val coupons: UiState<List<DtoCoupon>> = UiState.Loading,
    val topRated: UiState<List<DtoProduct>> = UiState.Loading,
    val bestSeller: UiState<List<DtoProduct>> = UiState.Loading,
    val mostLiked: UiState<List<DtoProduct>> = UiState.Loading
)

sealed interface MainIntent {
    data object Load : MainIntent
}
