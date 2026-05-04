package com.minhdk.wefashion.presentation.ui.screen.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import com.minhdk.wefashion.infrastructure.remote.model.api.account.request.RequestLoginAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepo: AccountRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> {
                _uiState.update { it.copy(emailOrPhone = intent.value) }
            }
            is LoginIntent.PasswordChanged -> {
                _uiState.update { it.copy(password = intent.value) }
            }
            LoginIntent.TogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            LoginIntent.ForgotPassword -> {
                viewModelScope.launch {
                    _effect.send(LoginEffect.NavigateForgotPassword)
                }
            }
            LoginIntent.Submit -> {
                submitLogin()
            }
        }
    }

    private fun submitLogin() {
        val current = _uiState.value
        if (current.emailOrPhone.isBlank() || current.password.isBlank()) {
            _effect.trySend(LoginEffect.ShowToast("Please fill all fields"))
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }

            val result = accountRepo.loginAccount(
                RequestLoginAccount(
                    username = current.emailOrPhone.trim(),
                    password = current.password
                )
            )

            when (result) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.send(LoginEffect.LoginSuccess)
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.send(LoginEffect.ShowToast("Login failed!"))
                }
            }
        }
    }
}