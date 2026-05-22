package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.order.DtoPaymentLink
import com.minhdk.wefashion.domain.data.order.DtoOrder
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseOrder
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseOrderList

fun ResponseCreateOrder.toDto(): DtoPaymentLink? {
    return DtoPaymentLink(
        orderId = orderId ?: return null,
        checkoutUrl = checkoutUrl ?: return null,
        qrCode = this.qrCode
    )
}

fun ResponseOrderList.toDtoOrders(): List<DtoOrder> {
    return orders.orEmpty().mapNotNull { it.toDto() }
}

fun ResponseOrder.toDto(): DtoOrder? {
    return DtoOrder(
        id = id ?: return null,
        discount = discount ?: return null,
        shippingFee = shippingFee ?: return null,
        totalPrice = totalPrice ?: return null,
        orderState = orderState ?: return null,
        shippingState = shippingState ?: return null,
        createdAt = createdAt ?: return null,
        userId = userId ?: return null,
        addressId = addressId ?: return null,
        paymentId = paymentId ?: return null,
        shipperId = shipperId ?: return null,
        productAmount = productAmount ?: return null
    )
}
