package com.minhdk.wefashion.presentation.ui.screen.authentication.login

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.minhdk.wefashion.presentation.ui.navigation.Authentication
import com.minhdk.wefashion.presentation.ui.screen.authentication.login.component.LoginContent

@Composable
fun LoginScreen(
    contentPadding: PaddingValues,
    onNavigate: (Authentication) -> Unit
) {

    val viewmodel: LoginViewModel = hiltViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateForgotPassword -> onNavigate(Authentication.ForgotPassword)
                is LoginEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is LoginEffect.LoginSuccess -> onNavigate(Authentication.Skip)
            }
        }
    }

    LoginContent(
        uiState = uiState,
        onEmailChange = { viewmodel.onIntent(LoginIntent.EmailChanged(it)) },
        onPasswordChange = { viewmodel.onIntent(LoginIntent.PasswordChanged(it)) },
        onTogglePassword = { viewmodel.onIntent(LoginIntent.TogglePasswordVisibility) },
        onForgotPassword = { viewmodel.onIntent(LoginIntent.ForgotPassword) },
        onSubmit = { viewmodel.onIntent(LoginIntent.Submit) },
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    )

}