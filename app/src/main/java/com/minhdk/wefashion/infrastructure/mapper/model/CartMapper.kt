package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.cart.DtoCart
import com.minhdk.wefashion.domain.data.cart.DtoCartDeleteResult
import com.minhdk.wefashion.domain.data.cart.DtoCartItem
import com.minhdk.wefashion.domain.data.cart.DtoCartSkuUpdateResult
import com.minhdk.wefashion.domain.data.product.DtoColor
import com.minhdk.wefashion.domain.data.product.DtoSize
import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCart
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCartColor
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCartProduct
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCartUser
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseDeleteCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseUpdateCartSku

fun ResponseCart.toDtoCart(): DtoCart? {
    val userDto = user?.toDtoUser() ?: return null
    val products = products.orEmpty()
    val items = products.flatMap { product ->
        product.skus.orEmpty().mapNotNull { sku ->
            sku.toDtoCartItem(product)
        }
    }
    return DtoCart(
        user = userDto,
        items = items
    )
}

private fun ResponseCartUser.toDtoUser(): DtoUser? {
    return if (id == null || name == null || avatarUrl == null || email == null || phoneNumber == null || bio == null) {
        null
    } else {
        DtoUser(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            email = email,
            phoneNumber = phoneNumber,
            bio = bio
        )
    }
}

private fun ResponseCartSku.toDtoCartItem(product: ResponseCartProduct): DtoCartItem? {
    val productId = product.id ?: return null
    val productName = product.name ?: return null
    val productImageUrl = product.imageUrl ?: return null
    val skuId = sku ?: return null
    val quantity = amount ?: return null
    val priceValue = price ?: return null
    val sizeDto = size?.toDtoSize() ?: return null
    val colorDto = color?.toDtoColor() ?: return null

    return DtoCartItem(
        productId = productId,
        productName = productName,
        productImageUrl = productImageUrl,
        sku = skuId,
        quantity = quantity,
        price = priceValue,
        size = sizeDto,
        color = colorDto
    )
}

private fun ResponseCartColor.toDtoColor(): DtoColor? {
    return if (id == null || rgb == null) null else DtoColor(id = id, rgb = rgb)
}

private fun com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCartSize.toDtoSize(): DtoSize? {
    return if (id == null || name == null) null else DtoSize(id = id, name = name)
}

fun ResponseUpdateCartSku.toDtoCartSkuUpdateResult(): DtoCartSkuUpdateResult {
    return DtoCartSkuUpdateResult(
        applied = applied ?: 0,
        expected = expected ?: 0
    )
}

fun ResponseDeleteCartSku.toDtoCartDeleteResult(): DtoCartDeleteResult {
    return DtoCartDeleteResult(
        success = success ?: false,
        message = message ?: ""
    )
}

