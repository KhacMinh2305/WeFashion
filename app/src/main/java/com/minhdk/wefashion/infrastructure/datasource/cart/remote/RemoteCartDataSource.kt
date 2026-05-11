package com.minhdk.wefashion.infrastructure.datasource.cart.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.cart.request.RequestUpdateCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCart
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseDeleteCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseUpdateCartSku

interface RemoteCartDataSource {

    suspend fun getCart(userId: Int): ResponseCart?

    suspend fun updateSkuQuantity(
        userId: Int,
        body: RequestUpdateCartSku
    ): ResponseUpdateCartSku?

    suspend fun deleteSku(
        userId: Int,
        sku: Int
    ): ResponseDeleteCartSku?
}

