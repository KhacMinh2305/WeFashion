package com.minhdk.wefashion.presentation.ui.screen.authentication.register

data class RegisterUiState(
    val username: String = "",
    val emailOrPhone: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false
)

sealed interface RegisterIntent {
    data class UsernameChanged(val value: String) : RegisterIntent
    data class EmailChanged(val value: String) : RegisterIntent
    data class PasswordChanged(val value: String) : RegisterIntent
    data object TogglePasswordVisibility : RegisterIntent
    data object Submit : RegisterIntent
    data object Back : RegisterIntent
}

sealed interface RegisterEffect {
    data class ShowToast(val message: String) : RegisterEffect
    data object RegisterSuccess : RegisterEffect
    data object NavigateBack : RegisterEffect
}

