package com.minhdk.wefashion.presentation.ui.screen.authentication.verification

data class VerificationUiState(
    val code: String = "",
    val isSubmitting: Boolean = false,
    val resendRemainingSeconds: Int = 0
)

sealed interface VerificationIntent {
    data class CodeChanged(val value: String) : VerificationIntent
    data class Submit(val email: String, val credential: String) : VerificationIntent
    data object Resend : VerificationIntent
    data object Back : VerificationIntent
}

sealed interface VerificationEffect {
    data class ShowToast(val message: String) : VerificationEffect
    data object Success : VerificationEffect
    data object NavigateBack : VerificationEffect
}
