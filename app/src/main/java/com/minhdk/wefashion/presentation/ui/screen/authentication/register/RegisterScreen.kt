package com.minhdk.wefashion.presentation.ui.screen.authentication.register

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.minhdk.wefashion.presentation.ui.navigation.Authentication
import com.minhdk.wefashion.presentation.ui.screen.authentication.register.component.RegisterContent

@Composable
fun RegisterScreen(
    contentPadding: PaddingValues,
    onNavigate: (Authentication) -> Unit
) {
    val viewmodel: RegisterViewModel = hiltViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.effect.collect { effect ->
            when (effect) {
                is RegisterEffect.RegisterSuccess -> onNavigate(Authentication.End)
                is RegisterEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is RegisterEffect.NavigateBack -> onNavigate(Authentication.Back)
            }
        }
    }

    RegisterContent(
        uiState = uiState,
        onUsernameChange = { viewmodel.onIntent(RegisterIntent.UsernameChanged(it)) },
        onEmailChange = { viewmodel.onIntent(RegisterIntent.EmailChanged(it)) },
        onPasswordChange = { viewmodel.onIntent(RegisterIntent.PasswordChanged(it)) },
        onTogglePassword = { viewmodel.onIntent(RegisterIntent.TogglePasswordVisibility) },
        onSubmit = { viewmodel.onIntent(RegisterIntent.Submit) },
        onBack = { viewmodel.onIntent(RegisterIntent.Back) },
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    )
}

