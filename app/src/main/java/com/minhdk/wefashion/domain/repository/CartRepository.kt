package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.cart.DtoCartDeleteResult
import com.minhdk.wefashion.domain.data.cart.DtoCartSkuUpdateResult

interface CartRepository {

    suspend fun getCart(userId: Int): RequestResult<DtoCart?>

    suspend fun updateSkuQuantity(
        userId: Int,
        sku: Int,
        change: String,
        amount: Int
    ): RequestResult<DtoCartSkuUpdateResult>

    suspend fun deleteSku(
        userId: Int,
        sku: Int
    ): RequestResult<DtoCartDeleteResult>
}

