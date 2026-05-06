package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductDetail
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductList
import com.minhdk.wefashion.infrastructure.remote.model.api.product.response.ResponseProductsByShop
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @GET("/api/product")
    suspend fun getProducts(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): BaseResponse<ResponseProductList>

    @GET("/api/product/top-rated")
    suspend fun getTopRatedProducts(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): BaseResponse<ResponseProductList>

    @GET("/api/product/best-seller")
    suspend fun getBestSellerProducts(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): BaseResponse<ResponseProductList>

    @GET("/api/product/most-liked")
    suspend fun getMostLikedProducts(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): BaseResponse<ResponseProductList>

    @GET("/api/product/{id}/details")
    suspend fun getProductDetails(
        @Path("id") id: Int
    ): BaseResponse<ResponseProductDetail>

    @GET("/api/product/shop/{id}")
    suspend fun getProductsByShop(
        @Path("id") id: Int
    ): BaseResponse<ResponseProductsByShop>

}

