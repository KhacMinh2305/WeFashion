package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.product.DtoProduct
import com.minhdk.wefashion.domain.data.product.DtoProductDetail
import com.minhdk.wefashion.domain.data.product.DtoProductsByShop

interface ProductRepository {

    suspend fun getProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>>

    suspend fun getTopRatedProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>>

    suspend fun getBestSellerProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>>

    suspend fun getMostLikedProducts(limit: Int?, offset: Int?): RequestResult<List<DtoProduct>>

    suspend fun getProductDetails(id: Int): RequestResult<DtoProductDetail>

    suspend fun getProductsByShop(shopId: Int): RequestResult<DtoProductsByShop>

}

