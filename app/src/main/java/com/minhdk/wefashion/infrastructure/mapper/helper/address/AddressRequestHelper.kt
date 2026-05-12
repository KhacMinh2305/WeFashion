package com.minhdk.wefashion.infrastructure.mapper.helper.address

import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteCreateAddressRequest
import com.minhdk.wefashion.infrastructure.remote.model.api.address.request.RemoteUpdateAddressRequest

fun buildCreateAddressRequest(
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
): RemoteCreateAddressRequest {
    return RemoteCreateAddressRequest(
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
}

fun buildUpdateAddressRequest(
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
): RemoteUpdateAddressRequest {
    return RemoteUpdateAddressRequest(
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
}

