package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.domain.data.address.DtoAddressList
import com.minhdk.wefashion.domain.data.address.DtoDeleteAddressResult
import com.minhdk.wefashion.domain.repository.AddressRepository
import com.minhdk.wefashion.infrastructure.datasource.address.remote.RemoteAddressDataSource
import com.minhdk.wefashion.infrastructure.mapper.helper.address.buildCreateAddressRequest
import com.minhdk.wefashion.infrastructure.mapper.helper.address.buildUpdateAddressRequest
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoAddress
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoAddressList
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoDeleteAddressResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteAddressDataSource
): AddressRepository {

    override suspend fun getAddressesByUserId(userId: Int): RequestResult<DtoAddressList> {
        return try {
            val response = remoteSource.getAddressesByUserId(userId)
                ?: return RequestResult.Error(Exception("Data is null"))
            RequestResult.Success(response.toDtoAddressList())
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

    override suspend fun getAddressById(addressId: Int): RequestResult<DtoAddress> {
        return try {
            val response = remoteSource.getAddressById(addressId)
                ?: return RequestResult.Error(Exception("Data is null"))
            val dto = response.toDtoAddress() ?: return RequestResult.Error(Exception("Data is null"))
            RequestResult.Success(dto)
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

    override suspend fun createAddress(
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
    ): RequestResult<DtoAddress> {
        return try {
            val request = buildCreateAddressRequest(
                name = name,
                ward = ward,
                district = district,
                city = city,
                detail = detail,
                latitude = latitude,
                longitude = longitude,
                receiverName = receiverName,
                phone = phone,
                isDefault = isDefault,
                userId = userId
            )
            val response = remoteSource.createAddress(request)
                ?: return RequestResult.Error(Exception("Data is null"))
            val dto = response.toDtoAddress() ?: return RequestResult.Error(Exception("Data is null"))
            RequestResult.Success(dto)
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

    override suspend fun updateAddress(
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
    ): RequestResult<DtoAddress> {
        return try {
            val request = buildUpdateAddressRequest(
                name = name,
                ward = ward,
                district = district,
                city = city,
                detail = detail,
                latitude = latitude,
                longitude = longitude,
                receiverName = receiverName,
                phone = phone,
                isDefault = isDefault,
                userId = userId
            )
            val response = remoteSource.updateAddress(addressId, request)
                ?: return RequestResult.Error(Exception("Data is null"))
            val dto = response.toDtoAddress() ?: return RequestResult.Error(Exception("Data is null"))
            RequestResult.Success(dto)
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

    override suspend fun deleteAddress(addressId: Int): RequestResult<DtoDeleteAddressResult> {
        return try {
            val response = remoteSource.deleteAddress(addressId)
                ?: return RequestResult.Error(Exception("Data is null"))
            RequestResult.Success(response.toDtoDeleteAddressResult())
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }
}

