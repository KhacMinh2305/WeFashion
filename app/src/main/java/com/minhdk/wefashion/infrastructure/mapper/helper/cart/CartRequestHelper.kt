package com.minhdk.wefashion.infrastructure.mapper.helper.cart

import com.minhdk.wefashion.infrastructure.remote.model.api.cart.request.RequestUpdateCartSku

fun buildUpdateCartSkuRequest(
    sku: Int,
    change: String,
    amount: Int
): RequestUpdateCartSku {
    return RequestUpdateCartSku(
        sku = sku,
        change = change,
        amount = amount
    )
}

