package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RemoteCreateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RemoteUpdateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddress
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddressList
import com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteDeleteAddressResponse
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AccountApiService {

    @GET("/api/address/user/{id}")
    suspend fun getAddressesByUserId(@Path("id") userId: Int): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddressList>

    @GET("/api/address/{id}")
    suspend fun getAddressById(@Path("id") addressId: Int): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddress>

    @POST("/api/address/create")
    suspend fun createAddress(@Body body: com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RemoteCreateAddressRequest): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddress>

    @PUT("/api/address/update")
    suspend fun updateAddress(
        @Query("address_id") addressId: Int,
        @Body body: com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RemoteUpdateAddressRequest
    ): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddress>

    @DELETE("/api/address/{id}/delete")
    suspend fun deleteAddress(@Path("id") addressId: Int): com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteDeleteAddressResponse>
}