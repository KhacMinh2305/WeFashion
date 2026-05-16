package com.minhdk.wefashion.domain.data.order

data class DtoPaymentLink(
    val orderId: Int,
    val checkoutUrl: String,
    val qrCode: String?
)
