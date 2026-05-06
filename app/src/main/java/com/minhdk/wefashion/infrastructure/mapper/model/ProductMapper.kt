package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.product.DtoProduct
import com.minhdk.wefashion.domain.data.product.DtoProductColor
import com.minhdk.wefashion.domain.data.product.DtoProductDetail
import com.minhdk.wefashion.domain.data.product.DtoProductShop
import com.minhdk.wefashion.domain.data.product.DtoProductSize
import com.minhdk.wefashion.domain.data.product.DtoProductSku
import com.minhdk.wefashion.domain.data.product.DtoProductsByShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntitySku
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProduct
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductColor
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductDetail
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductDetailProduct
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductShop
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductSize
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductSku
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductsByShop

fun ResponseProduct.toEntityProduct(): EntityProduct? {
    return if (
        id == null || name == null || imageUrl == null || description == null ||
        rating == null || soldAmount == null || likedAmount == null || categoryId == null
    ) {
        null
    } else {
        EntityProduct(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            rating = rating,
            soldAmount = soldAmount,
            likedAmount = likedAmount,
            categoryId = categoryId,
            shopId = shopId
        )
    }
}

fun ResponseProductDetailProduct.toEntityProduct(): EntityProduct? {
    return if (
        id == null || name == null || imageUrl == null || description == null ||
        rating == null || soldAmount == null || likedAmount == null || categoryId == null
    ) {
        null
    } else {
        EntityProduct(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            rating = rating,
            soldAmount = soldAmount,
            likedAmount = likedAmount,
            categoryId = categoryId,
            shopId = null
        )
    }
}

fun ResponseProductShop.toEntityShop(): EntityShop? {
    return if (
        id == null || name == null || avatarUrl == null || email == null ||
        phoneNumber == null || bio == null || rateAmount == null || rating == null || followers == null
    ) {
        null
    } else {
        EntityShop(
            id = id,
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
}

fun ResponseProductSku.toEntitySku(productId: Int): EntitySku? {
    val sizeEntity = size?.toEntitySize() ?: return null
    val colorEntity = color?.toEntityColor() ?: return null
    return if (sku == null || amount == null || price == null) {
        null
    } else {
        EntitySku(
            sku = sku,
            amount = amount,
            price = price,
            productId = productId,
            sizeId = sizeEntity.id,
            sizeName = sizeEntity.name,
            colorId = colorEntity.id,
            colorRgb = colorEntity.rgb
        )
    }
}

private data class EntitySizeTemp(val id: Int, val name: String)
private data class EntityColorTemp(val id: Int, val rgb: String)

private fun ResponseProductSize.toEntitySize(): EntitySizeTemp? {
    return if (id == null || name == null) null else EntitySizeTemp(id, name)
}

private fun ResponseProductColor.toEntityColor(): EntityColorTemp? {
    return if (id == null || rgb == null) null else EntityColorTemp(id, rgb)
}

fun EntityProduct.toDtoProduct(): DtoProduct {
    return DtoProduct(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        rating = rating,
        soldAmount = soldAmount,
        likedAmount = likedAmount,
        categoryId = categoryId,
        shopId = shopId
    )
}

fun EntityShop.toDtoProductShop(): DtoProductShop {
    return DtoProductShop(
        id = id,
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

fun EntitySku.toDtoProductSku(): DtoProductSku {
    return DtoProductSku(
        sku = sku,
        amount = amount,
        price = price,
        size = DtoProductSize(id = sizeId, name = sizeName),
        color = DtoProductColor(id = colorId, rgb = colorRgb)
    )
}

fun ResponseProductDetail.toDtoProductDetail(): DtoProductDetail? {
    val detailProduct = product?.toEntityProduct() ?: return null
    val detailShop = shop?.toEntityShop() ?: return null
    val detailSkus = sku.orEmpty().mapNotNull { it.toEntitySku(detailProduct.id) }
    return DtoProductDetail(
        product = detailProduct.toDtoProduct(),
        sku = detailSkus.map { it.toDtoProductSku() },
        shop = detailShop.toDtoProductShop()
    )
}

fun ResponseProductsByShop.toDtoProductsByShop(): DtoProductsByShop? {
    val shopEntity = shop?.toEntityShop() ?: return null
    val products = products.orEmpty().mapNotNull { it.toEntityProduct() }
    return DtoProductsByShop(
        shop = shopEntity.toDtoProductShop(),
        products = products.map { it.toDtoProduct() }
    )
}

