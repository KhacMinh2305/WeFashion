package com.minhdk.wefashion.presentation.ui.screen.cart.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.domain.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SelectAddressViewModel @Inject constructor(
    private val addressRepo: AddressRepository,
    private val accountRepo: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<AddressEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        handle(AddressIntent.Load)
    }

    fun handle(intent: AddressIntent) {
        when (intent) {
            AddressIntent.Load -> loadAddresses()
            is AddressIntent.Select -> selectAddress(intent.addressId)
            AddressIntent.Confirm -> confirmSelection()
            is AddressIntent.Created -> loadAddresses(preferSelectedId = intent.addressId)
        }
    }

    private fun loadAddresses(preferSelectedId: Int? = null) {
        val userId = accountRepo.getCurrentAccount()?.id
        if (userId == null) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Vui long dang nhap") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = addressRepo.getAddressesByUserId(userId)) {
                is RequestResult.Success -> {
                    val addresses = result.data.addresses
                    val selected = preferSelectedId?.takeIf { id -> addresses.any { it.id == id } }
                        ?: addresses.firstOrNull { item -> item.isDefault }?.id
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            addresses = addresses,
                            selectedId = selected
                        )
                    }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.error.message) }
                }
            }
        }
    }

    private fun selectAddress(addressId: Int) {
        _uiState.update { it.copy(selectedId = addressId) }
    }

    private fun confirmSelection() {
        val selected = _uiState.value.addresses.firstOrNull { it.id == _uiState.value.selectedId }
            ?: return
        _effect.trySend(AddressEffect.ConfirmSelection(selected))
    }
}
