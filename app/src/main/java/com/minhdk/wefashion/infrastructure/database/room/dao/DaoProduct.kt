package com.minhdk.wefashion.infrastructure.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntitySku

@Dao
interface DaoProduct {

    @Insert(onConflict = REPLACE)
    suspend fun insertProducts(products: List<EntityProduct>): List<Long>

    @Insert(onConflict = REPLACE)
    suspend fun insertProduct(product: EntityProduct): Long

    @Insert(onConflict = REPLACE)
    suspend fun insertShop(shop: EntityShop): Long

    @Insert(onConflict = REPLACE)
    suspend fun insertSkus(skus: List<EntitySku>): List<Long>

    @Query("SELECT * FROM Product")
    suspend fun getProducts(): List<EntityProduct>

    @Query("SELECT * FROM Product WHERE id = :id LIMIT 1")
    suspend fun getProductById(id: Int): EntityProduct?

    @Query("SELECT * FROM Shop WHERE id = :id LIMIT 1")
    suspend fun getShopById(id: Int): EntityShop?

    @Query("SELECT * FROM Sku WHERE product_id = :productId")
    suspend fun getSkusByProductId(productId: Int): List<EntitySku>
}

