package com.minhdk.wefashion.presentation.ui.screen.order.list_order

import com.minhdk.wefashion.domain.data.order.DtoOrder

data class ListOrderState(
    val isLoading: Boolean = false,
    val orders: List<DtoOrder> = emptyList(),
    val errorMessage: String? = null
)

sealed class ListOrderIntent {
    data object LoadOrders : ListOrderIntent()
}

