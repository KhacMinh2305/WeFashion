package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.cart.request.RequestUpdateCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseCart
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseDeleteCartSku
import com.minhdk.wefashion.infrastructure.remote.model.api.cart.response.ResponseUpdateCartSku
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.Query

interface CartApiService {

    @GET("/api/cart/{id}")
    suspend fun getCart(
        @Path("id") userId: Int
    ): BaseResponse<ResponseCart>

    @POST("/api/cart/{id}/sku/update")
    suspend fun updateSkuQuantity(
        @Path("id") userId: Int,
        @Body body: RequestUpdateCartSku
    ): BaseResponse<ResponseUpdateCartSku>

    @DELETE("/api/cart/{id}/delete")
    suspend fun deleteSku(
        @Path("id") userId: Int,
        @Query("sku") sku: Int
    ): BaseResponse<ResponseDeleteCartSku>
}

