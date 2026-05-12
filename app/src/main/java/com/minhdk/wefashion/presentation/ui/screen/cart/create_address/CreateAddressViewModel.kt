package com.minhdk.wefashion.presentation.ui.screen.cart.create_address

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
class CreateAddressViewModel @Inject constructor(
    private val addressRepo: AddressRepository,
    private val accountRepo: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAddressState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<CreateAddressEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: CreateAddressIntent) {
        when (intent) {
            is CreateAddressIntent.NameChanged -> _uiState.update { it.copy(name = intent.value) }
            is CreateAddressIntent.WardChanged -> _uiState.update { it.copy(ward = intent.value) }
            is CreateAddressIntent.DistrictChanged -> _uiState.update { it.copy(district = intent.value) }
            is CreateAddressIntent.CityChanged -> _uiState.update { it.copy(city = intent.value) }
            is CreateAddressIntent.DetailChanged -> _uiState.update { it.copy(detail = intent.value) }
            is CreateAddressIntent.ReceiverNameChanged -> _uiState.update { it.copy(receiverName = intent.value) }
            is CreateAddressIntent.PhoneChanged -> _uiState.update { it.copy(phone = intent.value) }
            is CreateAddressIntent.PositionChanged -> _uiState.update {
                it.copy(latitude = intent.latitude, longitude = intent.longitude)
            }
            CreateAddressIntent.Submit -> submit()
        }
    }

    private fun submit() {
        val state = _uiState.value
        if (state.isSubmitting) return

        val userId = accountRepo.getCurrentAccount()?.id
        if (userId == null) {
            _effect.trySend(CreateAddressEffect.ShowToast("Vui long dang nhap"))
            return
        }

        val validationMessages = validate(state)
        if (validationMessages.isNotEmpty()) {
            validationMessages.forEach { message ->
                _effect.trySend(CreateAddressEffect.ShowToast(message))
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true, errorMessage = null) }
            when (val result = addressRepo.createAddress(
                name = state.name.trim(),
                ward = state.ward.trim(),
                district = state.district.trim(),
                city = state.city.trim(),
                detail = state.detail.trim(),
                latitude = state.latitude,
                longitude = state.longitude,
                receiverName = state.receiverName.trim(),
                phone = state.phone.trim(),
                isDefault = false,
                userId = userId
            )) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.trySend(CreateAddressEffect.Created(result.data))
                }
                is RequestResult.Error -> {
                    _uiState.update {
                        it.copy(isSubmitting = false, errorMessage = result.error.message)
                    }
                    _effect.trySend(CreateAddressEffect.ShowToast("Tao dia chi that bai"))
                }
            }
        }
    }

    private fun validate(state: CreateAddressState): List<String> {
        val messages = mutableListOf<String>()
        if (state.name.isBlank()) messages.add("Vui long nhap ten dia chi")
        if (state.ward.isBlank()) messages.add("Vui long nhap phuong")
        if (state.district.isBlank()) messages.add("Vui long nhap quan")
        if (state.city.isBlank()) messages.add("Vui long nhap thanh pho")
        if (state.detail.isBlank()) messages.add("Vui long nhap dia chi chi tiet")
        if (state.receiverName.isBlank()) messages.add("Vui long nhap ten nguoi nhan")
        if (state.phone.isBlank()) {
            messages.add("Vui long nhap so dien thoai")
        } else if (!state.phone.all { it.isDigit() }) {
            messages.add("So dien thoai khong hop le")
        }
        if (state.latitude.isNaN() || state.longitude.isNaN()) {
            messages.add("Toa do khong hop le")
        } else if (state.latitude < 0.0 || state.longitude < 0.0) {
            messages.add("Toa do phai lon hon 0")
        }
        return messages
    }
}
