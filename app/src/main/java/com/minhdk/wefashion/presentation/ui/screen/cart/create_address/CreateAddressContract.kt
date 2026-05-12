package com.minhdk.wefashion.presentation.ui.screen.cart.create_address

import com.minhdk.wefashion.domain.data.address.DtoAddress

data class CreateAddressState(
    val name: String = "",
    val ward: String = "",
    val district: String = "",
    val city: String = "",
    val detail: String = "",
    val receiverName: String = "",
    val phone: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
)

sealed class CreateAddressIntent {
    data class NameChanged(val value: String) : CreateAddressIntent()
    data class WardChanged(val value: String) : CreateAddressIntent()
    data class DistrictChanged(val value: String) : CreateAddressIntent()
    data class CityChanged(val value: String) : CreateAddressIntent()
    data class DetailChanged(val value: String) : CreateAddressIntent()
    data class ReceiverNameChanged(val value: String) : CreateAddressIntent()
    data class PhoneChanged(val value: String) : CreateAddressIntent()
    data class PositionChanged(val latitude: Double, val longitude: Double) : CreateAddressIntent()
    data object Submit : CreateAddressIntent()
}

sealed class CreateAddressEffect {
    data class ShowToast(val message: String) : CreateAddressEffect()
    data class Created(val address: DtoAddress) : CreateAddressEffect()
}

