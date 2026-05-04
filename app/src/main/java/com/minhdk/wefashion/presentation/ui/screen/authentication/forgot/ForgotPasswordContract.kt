package com.minhdk.wefashion.presentation.ui.screen.authentication.forgot

data class ForgotPasswordUiState(
    val email: String = "",
    val isSubmitting: Boolean = false
)

sealed interface ForgotPasswordIntent {
    data class EmailChanged(val value: String) : ForgotPasswordIntent
    data object Submit : ForgotPasswordIntent

    data object Back : ForgotPasswordIntent
}

sealed interface ForgotPasswordEffect {
    data class ShowToast(val message: String) : ForgotPasswordEffect
    data class SubmitSuccess(val credential: String) : ForgotPasswordEffect
    data object NavigateBack : ForgotPasswordEffect
}

