package com.minhdk.wefashion.domain.data.address

data class DtoAddressList(
    val user: DtoAddressUser?,
    val addresses: List<DtoAddress>
)

data class DtoAddressUser(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val email: String,
    val phoneNumber: String,
    val bio: String
)

data class DtoAddress(
    val id: Int,
    val name: String,
    val ward: String,
    val district: String,
    val city: String,
    val detail: String,
    val latitude: Double,
    val longitude: Double,
    val receiverName: String,
    val phone: String,
    val isDefault: Boolean,
    val userId: Int
)

data class DtoDeleteAddressResult(
    val message: String
)

