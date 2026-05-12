package com.minhdk.wefashion.infrastructure.datasource.address.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteCreateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteUpdateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddress
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddressList
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteDeleteAddressResponse
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class RemoteAddressDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
): RemoteAddressDataSource {

    override suspend fun getAddressesByUserId(userId: Int): RemoteAddressList? = withContext(Dispatchers.IO) {
        apiService.getAddressesByUserId(userId).data
    }

    override suspend fun getAddressById(addressId: Int): RemoteAddress? = withContext(Dispatchers.IO) {
        apiService.getAddressById(addressId).data
    }

    override suspend fun createAddress(body: RemoteCreateAddressRequest): RemoteAddress? = withContext(Dispatchers.IO) {
        apiService.createAddress(body).data
    }

    override suspend fun updateAddress(
        addressId: Int,
        body: RemoteUpdateAddressRequest
    ): RemoteAddress? = withContext(Dispatchers.IO) {
        apiService.updateAddress(addressId, body).data
    }

    override suspend fun deleteAddress(addressId: Int): RemoteDeleteAddressResponse? = withContext(Dispatchers.IO) {
        apiService.deleteAddress(addressId).data
    }
}

