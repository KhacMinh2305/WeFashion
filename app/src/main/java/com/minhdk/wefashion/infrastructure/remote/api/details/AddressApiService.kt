package com.minhdk.wefashion.infrastructure.remote.api.details

import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteCreateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteUpdateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddress
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddressList
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteDeleteAddressResponse
import com.minhdk.wefashion.infrastructure.remote.model.baseResponse.BaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AddressApiService {

    @GET("/api/address/user/{id}")
    suspend fun getAddressesByUserId(@Path("id") userId: Int): BaseResponse<RemoteAddressList>

    @GET("/api/address/{id}")
    suspend fun getAddressById(@Path("id") addressId: Int): BaseResponse<RemoteAddress>

    @POST("/api/address/create")
    suspend fun createAddress(@Body body: RemoteCreateAddressRequest): BaseResponse<RemoteAddress>

    @PUT("/api/address/update")
    suspend fun updateAddress(
        @Query("address_id") addressId: Int,
        @Body body: RemoteUpdateAddressRequest
    ): BaseResponse<RemoteAddress>

    @DELETE("/api/address/{id}/delete")
    suspend fun deleteAddress(@Path("id") addressId: Int): BaseResponse<RemoteDeleteAddressResponse>
}