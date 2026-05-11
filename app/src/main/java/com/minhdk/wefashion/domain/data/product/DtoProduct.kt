package com.minhdk.wefashion.domain.data.product

import android.graphics.Color

data class DtoProduct(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val rating: Double,
    val soldAmount: Int,
    val likedAmount: Int,
    val categoryId: Int,
    val shopId: Int?
)

data class DtoSize(
    val id: Int,
    val name: String
)

data class DtoColor(
    val id: Int,
    val rgb: String
) {

        fun toColor(): List<Int>? {
            return try {
                val (r, g, b) = rgb.split(",").map { it.trim().toInt() }
                listOf(r, g, b)
            } catch (e: IllegalArgumentException) {
                null
            }
        }

}

data class DtoProductSku(
    val sku: Int,
    val amount: Int,
    val price: Int,
    val size: DtoSize,
    val color: DtoColor
)

data class DtoProductShop(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val email: String,
    val phoneNumber: String,
    val bio: String,
    val rateAmount: Int,
    val rating: Double,
    val followers: Int
)

data class DtoProductDetail(
    val product: DtoProduct,
    val sku: List<DtoProductSku>,
    val shop: DtoProductShop
)

data class DtoProductsByShop(
    val shop: DtoProductShop,
    val products: List<DtoProduct>
)

