package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApiService {

    @POST("api/order/user/{id}/create")
    suspend fun createOrder(
        @Path("id") userId: Int,
        @Body body: RequestCreateOrderBody
    ): BaseResponse<ResponseCreateOrder>

}