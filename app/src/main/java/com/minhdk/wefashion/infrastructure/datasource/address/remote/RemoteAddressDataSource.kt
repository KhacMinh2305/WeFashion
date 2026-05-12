package com.minhdk.wefashion.infrastructure.datasource.address.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteCreateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteUpdateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddress
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddressList
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteDeleteAddressResponse

interface RemoteAddressDataSource {

    suspend fun getAddressesByUserId(userId: Int): RemoteAddressList?

    suspend fun getAddressById(addressId: Int): RemoteAddress?

    suspend fun createAddress(body: RemoteCreateAddressRequest): RemoteAddress?

    suspend fun updateAddress(addressId: Int, body: RemoteUpdateAddressRequest): RemoteAddress?

    suspend fun deleteAddress(addressId: Int): RemoteDeleteAddressResponse?
}

