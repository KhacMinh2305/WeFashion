package com.minhdk.wefashion.infrastructure.mapper.model

import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.domain.data.address.DtoAddressList
import com.minhdk.wefashion.domain.data.address.DtoAddressUser
import com.minhdk.wefashion.domain.data.address.DtoDeleteAddressResult
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddress
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddressList
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteAddressUser
import com.minhdk.wefashion.infrastructure.remote.model.api.address.response.RemoteDeleteAddressResponse

fun RemoteAddressList.toDtoAddressList(): DtoAddressList {
    return DtoAddressList(
        user = user?.toDtoAddressUser(),
        addresses = addresses.mapNotNull { it?.toDtoAddress() }
    )
}

fun RemoteAddress.toDtoAddress(): DtoAddress? {
    return if (
        id == null || name == null || ward == null || district == null || city == null || detail == null ||
        latitude == null || longitude == null || receiverName == null || phone == null ||
        isDefault == null || userId == null
    ) {
        null
    } else {
        DtoAddress(
            id = id,
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
}

private fun RemoteAddressUser.toDtoAddressUser(): DtoAddressUser? {
    return if (id == null || name == null || avatarUrl == null || email == null || phoneNumber == null || bio == null) {
        null
    } else {
        DtoAddressUser(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            email = email,
            phoneNumber = phoneNumber,
            bio = bio
        )
    }
}

fun RemoteDeleteAddressResponse.toDtoDeleteAddressResult(): DtoDeleteAddressResult {
    return DtoDeleteAddressResult(
        message = message ?: ""
    )
}

