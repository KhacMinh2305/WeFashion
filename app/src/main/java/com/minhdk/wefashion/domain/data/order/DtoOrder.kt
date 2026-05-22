package com.minhdk.wefashion.domain.data.order

data class DtoOrder(
    val id: Int,
    val discount: Int,
    val shippingFee: Int,
    val totalPrice: Int,
    val orderState: Int,
    val shippingState: Int,
    val createdAt: String,
    val userId: Int,
    val addressId: Int,
    val paymentId: Int,
    val shipperId: Int,
    val productAmount: Int
)

