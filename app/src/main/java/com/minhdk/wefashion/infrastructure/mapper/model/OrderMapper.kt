package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.order.DtoPaymentLink
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder

fun ResponseCreateOrder.toDto(): DtoPaymentLink? {
    return DtoPaymentLink(
        orderId = orderId ?: return null,
        checkoutUrl = checkoutUrl ?: return null,
        qrCode = this.qrCode
    )
}