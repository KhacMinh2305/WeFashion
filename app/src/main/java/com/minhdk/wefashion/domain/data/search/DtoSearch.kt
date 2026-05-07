package com.minhdk.wefashion.domain.data.search

data class DtoSearchResult(
    val products: List<DtoSearchProduct>,
    val shops: List<DtoSearchShop>
)

data class DtoSearchProduct(
    val id: Int,
    val name: String?,
    val imageUrl: String?,
    val description: String?,
    val rating: Double?,
    val soldAmount: Int?,
    val likedAmount: Int?,
    val categoryId: Int,
    val shopId: Int
)

data class DtoSearchShop(
    val id: Int,
    val name: String?,
    val avatarUrl: String?,
    val email: String?,
    val phoneNumber: String?,
    val bio: String?,
    val rateAmount: Int?,
    val rating: Double?,
    val followers: Int?
)
