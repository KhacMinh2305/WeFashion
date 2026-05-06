package com.minhdk.wefashion.infrastructure.datasource.product.local

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntitySku

interface LocalProductDataSource {

    suspend fun insertProducts(products: List<EntityProduct>): List<Long>

    suspend fun insertProduct(product: EntityProduct): Long

    suspend fun insertShop(shop: EntityShop): Long

    suspend fun insertSkus(skus: List<EntitySku>): List<Long>

    suspend fun getProducts(): List<EntityProduct>

    suspend fun getProductById(id: Int): EntityProduct?

    suspend fun getShopById(id: Int): EntityShop?

    suspend fun getSkusByProductId(productId: Int): List<EntitySku>

}

