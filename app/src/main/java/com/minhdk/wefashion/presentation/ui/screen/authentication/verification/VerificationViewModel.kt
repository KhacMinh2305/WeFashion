package com.minhdk.wefashion.presentation.ui.screen.authentication.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : ViewModel() {

    companion object {
        private const val CODE_LENGTH = 4
        private const val RESEND_COOLDOWN_SECONDS = 60
        private const val ONE_SECOND_MS = 1000L
    }

    private val _uiState = MutableStateFlow(VerificationUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<VerificationEffect>()
    val effect = _effect.receiveAsFlow()

    private var resendJob: Job? = null

    fun onIntent(intent: VerificationIntent) {
        when (intent) {
            is VerificationIntent.CodeChanged -> {
                _uiState.update { it.copy(code = sanitizeCode(intent.value)) }
            }
            is VerificationIntent.Submit -> {
                submitVerification(intent.email, intent.credential)
            }
            VerificationIntent.Resend -> {
                handleResend()
            }
            VerificationIntent.Back -> {
                _effect.trySend(VerificationEffect.NavigateBack)
            }
        }
    }

    private fun submitVerification(email: String, credential: String) {
        val current = _uiState.value
        if (current.code.length < CODE_LENGTH) {
            _effect.trySend(VerificationEffect.ShowToast("Please enter the full code"))
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }

            val result = accountRepo.validateForgotPassword(
                email = email.trim(),
                code = current.code,
                credential = credential
            )

            when (result) {
                is RequestResult.Success -> {
                    val isValid = result.data?.isValid == true
                    if (isValid) {
                        _effect.send(VerificationEffect.Success)
                    } else {
                        val message = result.data?.detail ?: "Invalid verification code"
                        _effect.send(VerificationEffect.ShowToast(message))
                    }
                }
                is RequestResult.Error -> {
                    val message = result.error.message ?: "Verification failed"
                    _effect.send(VerificationEffect.ShowToast(message))
                }
            }

            _uiState.update { it.copy(isSubmitting = false) }
        }
    }

    private fun handleResend() {
        if (_uiState.value.resendRemainingSeconds > 0) return
        if (resendJob?.isActive == true) return

        _effect.trySend(VerificationEffect.ShowToast("Code resent"))
        resendJob = viewModelScope.launch {
            for (seconds in RESEND_COOLDOWN_SECONDS downTo 1) {
                _uiState.update { it.copy(resendRemainingSeconds = seconds) }
                delay(ONE_SECOND_MS)
            }
            _uiState.update { it.copy(resendRemainingSeconds = 0) }
        }
    }

    private fun sanitizeCode(value: String): String {
        return value.filter { it.isDigit() }.take(CODE_LENGTH)
    }
}
