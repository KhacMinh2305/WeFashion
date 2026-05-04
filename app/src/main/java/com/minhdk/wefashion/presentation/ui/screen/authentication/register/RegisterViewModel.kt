package com.minhdk.wefashion.presentation.ui.screen.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.UsernameChanged -> {
                _uiState.update { it.copy(username = intent.value) }
            }
            is RegisterIntent.EmailChanged -> {
                _uiState.update { it.copy(emailOrPhone = intent.value) }
            }
            is RegisterIntent.PasswordChanged -> {
                _uiState.update { it.copy(password = intent.value) }
            }
            RegisterIntent.TogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            RegisterIntent.Submit -> {
                submitRegister()
            }
            RegisterIntent.Back -> {
                _effect.trySend(RegisterEffect.NavigateBack)
            }
        }
    }

    private fun submitRegister() {
        val current = _uiState.value
        if (current.username.isBlank() || current.emailOrPhone.isBlank() || current.password.isBlank()) {
            _effect.trySend(RegisterEffect.ShowToast("Please fill all fields"))
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }

            val result = accountRepo.registerAccount(
                email = current.emailOrPhone.trim(),
                username = current.username.trim(),
                password = current.password
            )

            when (result) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.send(RegisterEffect.RegisterSuccess)
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.send(RegisterEffect.ShowToast("Register failed!"))
                }
            }
        }
    }
}

