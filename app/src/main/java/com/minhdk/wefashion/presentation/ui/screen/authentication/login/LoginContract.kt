package com.minhdk.wefashion.presentation.ui.screen.authentication.login

data class LoginUiState(
    val username: String = "",
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
    data object Register : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateForgotPassword : LoginEffect
    data object NavigateToRegister : LoginEffect
    data class ShowToast(val message: String) : LoginEffect
    data object LoginSuccess: LoginEffect
}
