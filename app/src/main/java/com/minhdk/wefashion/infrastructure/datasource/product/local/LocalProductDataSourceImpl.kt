package com.minhdk.wefashion.infrastructure.datasource.product.local

import com.minhdk.wefashion.infrastructure.database.room.dao.DaoProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntitySku
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class LocalProductDataSourceImpl @Inject constructor(
    private val daoProduct: DaoProduct
) : LocalProductDataSource {

    override suspend fun insertProducts(products: List<EntityProduct>): List<Long> = withContext(Dispatchers.IO) {
        daoProduct.insertProducts(products)
    }

    override suspend fun insertProduct(product: EntityProduct): Long = withContext(Dispatchers.IO) {
        daoProduct.insertProduct(product)
    }

    override suspend fun insertShop(shop: EntityShop): Long = withContext(Dispatchers.IO) {
        daoProduct.insertShop(shop)
    }

    override suspend fun insertSkus(skus: List<EntitySku>): List<Long> = withContext(Dispatchers.IO) {
        daoProduct.insertSkus(skus)
    }

    override suspend fun getProducts(): List<EntityProduct> = withContext(Dispatchers.IO) {
        daoProduct.getProducts()
    }

    override suspend fun getProductById(id: Int): EntityProduct? = withContext(Dispatchers.IO) {
        daoProduct.getProductById(id)
    }

    override suspend fun getShopById(id: Int): EntityShop? = withContext(Dispatchers.IO) {
        daoProduct.getShopById(id)
    }

    override suspend fun getSkusByProductId(productId: Int): List<EntitySku> = withContext(Dispatchers.IO) {
        daoProduct.getSkusByProductId(productId)
    }

}

