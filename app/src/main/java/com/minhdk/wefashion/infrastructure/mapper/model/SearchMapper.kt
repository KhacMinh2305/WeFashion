package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.search.DtoSearchProduct
import com.minhdk.wefashion.domain.data.search.DtoSearchResult
import com.minhdk.wefashion.domain.data.search.DtoSearchShop
import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchData
import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchProduct
import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchShop

fun ResponseSearchShop.toDto(): DtoSearchShop {
    return DtoSearchShop(
        id = id ?: throw Exception("id is null"),
        name = name,
        avatarUrl = avatarUrl,
        email = email,
        phoneNumber = phoneNumber,
        bio = bio,
        rateAmount = rateAmount,
        rating = rating,
        followers = followers
    )
}

fun ResponseSearchProduct.toDto(): DtoSearchProduct {
    return DtoSearchProduct(
        id = id ?: throw Exception("id is null"),
        name = name,
        imageUrl = imageUrl,
        description = description,
        rating = rating,
        soldAmount = soldAmount,
        likedAmount = likedAmount,
        categoryId = categoryId ?: throw Exception("categoryId is null"),
        shopId = shopId ?: throw Exception("shopId is null")
    )
}

fun ResponseSearchData.toDto(): DtoSearchResult {
    return DtoSearchResult(
        products = products?.map { it.toDto() } ?: emptyList(),
        shops = shops?.map { it.toDto() } ?: emptyList()
    )
}
