package com.minhdk.wefashion.infrastructure.datasource.product.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductDetail
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductList
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductsByShop
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class RemoteProductDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
) : RemoteProductDataSource {

    override suspend fun getProducts(
        limit: Int?,
        offset: Int?
    ): ResponseProductList? = withContext(Dispatchers.IO) {
        apiService.getProducts(limit, offset).data
    }

    override suspend fun getTopRatedProducts(
        limit: Int?,
        offset: Int?
    ): ResponseProductList? = withContext(Dispatchers.IO) {
        apiService.getTopRatedProducts(limit, offset).data
    }

    override suspend fun getBestSellerProducts(
        limit: Int?,
        offset: Int?
    ): ResponseProductList? = withContext(Dispatchers.IO) {
        apiService.getBestSellerProducts(limit, offset).data
    }

    override suspend fun getMostLikedProducts(
        limit: Int?,
        offset: Int?
    ): ResponseProductList? = withContext(Dispatchers.IO) {
        apiService.getMostLikedProducts(limit, offset).data
    }

    override suspend fun getProductDetails(id: Int): ResponseProductDetail? = withContext(Dispatchers.IO) {
        apiService.getProductDetails(id).data
    }

    override suspend fun getProductsByShop(id: Int): ResponseProductsByShop? = withContext(Dispatchers.IO) {
        apiService.getProductsByShop(id).data
    }

}

