package com.minhdk.wefashion.infrastructure.mapper.helper.order

import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderItemBody

fun buildRequestCreateOrderBody(
    discount: Int,
    shippingFee: Int,
    totalPrice: Int,
    userId: Int,
    addressId: Int,
    cart: DtoCart
): RequestCreateOrderBody {
    return RequestCreateOrderBody(
        discount = discount,
        shippingFee = shippingFee,
        totalPrice = totalPrice,
        userId = userId,
        addressId = addressId,
        items = buildRequestCreateOrderItemBody(cart)
    )
}

private fun buildRequestCreateOrderItemBody(
    cart: DtoCart
): List<RequestCreateOrderItemBody> {
    return cart.items.map {
        RequestCreateOrderItemBody(
            sku = it.sku,
            amount = it.quantity,
            price = it.price,
            productId = it.productId,
            sizeId = it.size.id,
            colorId = it.color.id
        )
    }
}