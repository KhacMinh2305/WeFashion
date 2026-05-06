package com.minhdk.wefashion.infrastructure.datasource.product.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductDetail
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductList
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductsByShop

interface RemoteProductDataSource {

    suspend fun getProducts(limit: Int?, offset: Int?): ResponseProductList?

    suspend fun getTopRatedProducts(limit: Int?, offset: Int?): ResponseProductList?

    suspend fun getBestSellerProducts(limit: Int?, offset: Int?): ResponseProductList?

    suspend fun getMostLikedProducts(limit: Int?, offset: Int?): ResponseProductList?

    suspend fun getProductDetails(id: Int): ResponseProductDetail?

    suspend fun getProductsByShop(id: Int): ResponseProductsByShop?

}

