package com.minhdk.wefashion.presentation.ui.screen.authentication.verification

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
import com.minhdk.wefashion.presentation.ui.screen.authentication.verification.component.VerificationContent

@Composable
fun VerificationScreen(
    contentPadding: PaddingValues,
    email: String,
    credential: String,
    onNavigate: (Authentication) -> Unit
) {
    val viewmodel: VerificationViewModel = hiltViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.effect.collect { effect ->
            when (effect) {
                is VerificationEffect.Success -> {
                    onNavigate(Authentication.Reset)
                }
                is VerificationEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is VerificationEffect.NavigateBack -> {
                    onNavigate(Authentication.Back)
                }
            }
        }
    }

    VerificationContent(
        uiState = uiState,
        email = email,
        onCodeChange = { viewmodel.onIntent(VerificationIntent.CodeChanged(it)) },
        onSubmit = { viewmodel.onIntent(VerificationIntent.Submit(email, credential)) },
        onResend = { viewmodel.onIntent(VerificationIntent.Resend) },
        onBack = { viewmodel.onIntent(VerificationIntent.Back) },
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    )
}
