package com.minhdk.wefashion.domain.data.product

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

data class DtoProductSize(
    val id: Int,
    val name: String
)

data class DtoProductColor(
    val id: Int,
    val rgb: String
)

data class DtoProductSku(
    val sku: Int,
    val amount: Int,
    val price: Int,
    val size: DtoProductSize,
    val color: DtoProductColor
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

