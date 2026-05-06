package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.product.DtoProduct
import com.minhdk.wefashion.domain.data.product.DtoProductDetail
import com.minhdk.wefashion.domain.data.product.DtoProductsByShop
import com.minhdk.wefashion.domain.repository.ProductRepository
import com.minhdk.wefashion.infrastructure.datasource.product.local.LocalProductDataSource
import com.minhdk.wefashion.infrastructure.datasource.product.remote.RemoteProductDataSource
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoProduct
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoProductShop
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoProductSku
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoProductsByShop
import com.minhdk.wefashion.infrastructure.mapper.model.toEntityProduct
import com.minhdk.wefashion.infrastructure.mapper.model.toEntityShop
import com.minhdk.wefashion.infrastructure.mapper.model.toEntitySku
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val localSource: LocalProductDataSource,
    private val remoteSource: RemoteProductDataSource
) : ProductRepository {

    override suspend fun getProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>> {
        return try {
            val localProducts = localSource.getProducts()
            if (localProducts.isNotEmpty()) {
                return RequestResult.Success(localProducts.map { it.toDtoProduct() })
            }

            val response = remoteSource.getProducts(limit, offset)
            val remoteProducts = response?.data.orEmpty().mapNotNull { it.toEntityProduct() }
            if (remoteProducts.isNotEmpty()) {
                localSource.insertProducts(remoteProducts)
            }
            RequestResult.Success(remoteProducts.map { it.toDtoProduct() })
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getTopRatedProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>> {
        return try {
            val response = remoteSource.getTopRatedProducts(limit, offset)
            val products = response?.data.orEmpty().mapNotNull { it.toEntityProduct() }
            if (products.isNotEmpty()) {
                localSource.insertProducts(products)
            }
            RequestResult.Success(products.map { it.toDtoProduct() })
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getBestSellerProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>> {
        return try {
            val response = remoteSource.getBestSellerProducts(limit, offset)
            val products = response?.data.orEmpty().mapNotNull { it.toEntityProduct() }
            if (products.isNotEmpty()) {
                localSource.insertProducts(products)
            }
            RequestResult.Success(products.map { it.toDtoProduct() })
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getMostLikedProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>> {
        return try {
            val response = remoteSource.getMostLikedProducts(limit, offset)
            val products = response?.data.orEmpty().mapNotNull { it.toEntityProduct() }
            if (products.isNotEmpty()) {
                localSource.insertProducts(products)
            }
            RequestResult.Success(products.map { it.toDtoProduct() })
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getProductDetails(id: Int): RequestResult<DtoProductDetail> {
        return try {
            val localProduct = localSource.getProductById(id)
            val localSkus = localSource.getSkusByProductId(id)
            val localShop = localProduct?.shopId?.let { localSource.getShopById(it) }
            if (localProduct != null && localShop != null && localSkus.isNotEmpty()) {
                val detail = DtoProductDetail(
                    product = localProduct.toDtoProduct(),
                    sku = localSkus.map { it.toDtoProductSku() },
                    shop = localShop.toDtoProductShop()
                )
                return RequestResult.Success(detail)
            }

            val response = remoteSource.getProductDetails(id)
                ?: throw NoSuchElementException("Product not found for id=$id")
            val baseProduct = response.product?.toEntityProduct() ?: throw Exception("Important field is null")
            val shopEntity = response.shop?.toEntityShop() ?: throw Exception("Important field is null")
            val productEntity = baseProduct.copy(shopId = shopEntity.id)
            val skus = response.sku.orEmpty().mapNotNull { it.toEntitySku(productEntity.id) }
            localSource.insertProduct(productEntity)
            localSource.insertShop(shopEntity)
            if (skus.isNotEmpty()) localSource.insertSkus(skus)

            val detail = DtoProductDetail(
                product = productEntity.toDtoProduct(),
                sku = skus.map { it.toDtoProductSku() },
                shop = shopEntity.toDtoProductShop()
            )
            RequestResult.Success(detail)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun getProductsByShop(shopId: Int): RequestResult<DtoProductsByShop> {
        return try {
            val response = remoteSource.getProductsByShop(shopId)
                ?: throw NoSuchElementException("Shop products not found for id=$shopId")
            val dto = response.toDtoProductsByShop()
                ?: throw Exception("Important field is null")

            val shopEntity = response.shop?.toEntityShop() ?: throw Exception("Important field is null")
            val products = response.products.orEmpty().mapNotNull { it.toEntityProduct() }
            localSource.insertShop(shopEntity)
            if (products.isNotEmpty()) {
                localSource.insertProducts(products)
            }

            RequestResult.Success(dto)
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

}



