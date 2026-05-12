package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.domain.data.address.DtoAddressList
import com.minhdk.wefashion.domain.data.address.DtoDeleteAddressResult

interface AddressRepository {

    suspend fun getAddressesByUserId(userId: Int): RequestResult<DtoAddressList>

    suspend fun getAddressById(addressId: Int): RequestResult<DtoAddress>

    suspend fun createAddress(
        name: String,
        ward: String,
        district: String,
        city: String,
        detail: String,
        latitude: Double,
        longitude: Double,
        receiverName: String,
        phone: String,
        isDefault: Boolean,
        userId: Int
    ): RequestResult<DtoAddress>

    suspend fun updateAddress(
        addressId: Int,
        name: String,
        ward: String,
        district: String,
        city: String,
        detail: String,
        latitude: Double,
        longitude: Double,
        receiverName: String,
        phone: String,
        isDefault: Boolean,
        userId: Int
    ): RequestResult<DtoAddress>

    suspend fun deleteAddress(addressId: Int): RequestResult<DtoDeleteAddressResult>
}

