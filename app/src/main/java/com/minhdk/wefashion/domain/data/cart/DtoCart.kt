package com.minhdk.wefashion.domain.data.cart

import com.minhdk.wefashion.domain.data.product.DtoColor
import com.minhdk.wefashion.domain.data.product.DtoSize
import com.minhdk.wefashion.domain.data.user.DtoUser

data class DtoCart(
    val user: DtoUser,
    val items: List<DtoCartItem>
)

data class DtoCartItem(
    val productId: Int,
    val productName: String,
    val productImageUrl: String,
    val sku: Int,
    val quantity: Int,
    val price: Int,
    val size: DtoSize,
    val color: DtoColor
)

data class DtoCartSkuUpdateResult(
    val applied: Int,
    val expected: Int
)

data class DtoCartDeleteResult(
    val success: Boolean,
    val message: String
)

