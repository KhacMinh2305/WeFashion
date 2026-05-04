package com.minhdk.wefashion.presentation.ui.screen.authentication.login

data class LoginUiState(
    val emailOrPhone: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false
)

sealed interface LoginIntent {
    data class EmailChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
    data object ForgotPassword : LoginIntent
    data object Submit : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateForgotPassword : LoginEffect
    data class ShowToast(val message: String) : LoginEffect
    data object LoginSuccess: LoginEffect
}
