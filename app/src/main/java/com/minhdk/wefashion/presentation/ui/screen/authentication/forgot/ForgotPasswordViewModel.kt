package com.minhdk.wefashion.presentation.ui.screen.authentication.forgot

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
class ForgotPasswordViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<ForgotPasswordEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: ForgotPasswordIntent) {
        when (intent) {
            is ForgotPasswordIntent.EmailChanged -> {
                _uiState.update { it.copy(email = intent.value) }
            }
            is ForgotPasswordIntent.Submit -> {
                submitForgotPassword()
            }
            is ForgotPasswordIntent.Back -> {
                _effect.trySend(ForgotPasswordEffect.NavigateBack)
            }
        }
    }

    private fun submitForgotPassword() {
        val current = _uiState.value
        if (current.email.isBlank()) {
            _effect.trySend(ForgotPasswordEffect.ShowToast("Please fill all fields"))
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }

            val result = accountRepo.forgotPassword(
                email = current.email.trim()
            )

            when (result) {
                is RequestResult.Success -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    result.data?.credential?.let {
                        _effect.send(ForgotPasswordEffect.SubmitSuccess(it))
                    }  ?: run {
                        _effect.send(ForgotPasswordEffect.ShowToast("Send code failed!"))
                    }
                }
                is RequestResult.Error -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _effect.send(ForgotPasswordEffect.ShowToast("Send code failed!"))
                }
            }
        }
    }
}

