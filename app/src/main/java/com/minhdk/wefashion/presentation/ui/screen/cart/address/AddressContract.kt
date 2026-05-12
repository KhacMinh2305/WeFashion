package com.minhdk.wefashion.presentation.ui.screen.cart.address

import com.minhdk.wefashion.domain.data.address.DtoAddress

data class AddressState(
    val isLoading: Boolean = false,
    val addresses: List<DtoAddress> = emptyList(),
    val selectedId: Int? = null,
    val errorMessage: String? = null
)

sealed class AddressIntent {
    data object Load : AddressIntent()
    data class Select(val addressId: Int) : AddressIntent()
    data object Confirm : AddressIntent()
    data class Created(val addressId: Int) : AddressIntent()
}

sealed class AddressEffect {
    data class ConfirmSelection(val address: DtoAddress) : AddressEffect()
}
