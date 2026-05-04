package com.minhdk.wefashion.presentation.ui.screen.authentication.forgot

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
import com.minhdk.wefashion.presentation.ui.screen.authentication.forgot.component.ForgotPasswordContent

@Composable
fun ForgotPasswordScreen(
    contentPadding: PaddingValues,
    onNavigate: (Authentication) -> Unit
) {
    val viewmodel: ForgotPasswordViewModel = hiltViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.effect.collect { effect ->
            when (effect) {
                is ForgotPasswordEffect.SubmitSuccess -> {
                    onNavigate(Authentication.Verification(uiState.email, effect.credential))
                }
                is ForgotPasswordEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is ForgotPasswordEffect.NavigateBack -> {
                    onNavigate(Authentication.Back)
                }
            }
        }
    }

    ForgotPasswordContent(
        uiState = uiState,
        onEmailChange = { viewmodel.onIntent(ForgotPasswordIntent.EmailChanged(it)) },
        onSubmit = { viewmodel.onIntent(ForgotPasswordIntent.Submit) },
        onBack = { viewmodel.onIntent(ForgotPasswordIntent.Back) },
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    )
}

