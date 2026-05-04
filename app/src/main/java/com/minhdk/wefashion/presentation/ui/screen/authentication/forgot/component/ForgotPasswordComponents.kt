package com.minhdk.wefashion.presentation.ui.screen.authentication.forgot.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.screen.authentication.forgot.ForgotPasswordUiState
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun ForgotPasswordContent(
    uiState: ForgotPasswordUiState,
    onEmailChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(Background)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ForgotPasswordHeader()
        Spacer(modifier = Modifier.height(24.dp))
        ForgotPasswordForm(
            email = uiState.email,
            onEmailChange = onEmailChange
        )
        Spacer(modifier = Modifier.height(28.dp))

        BaseButtonBox(
            txt = if (uiState.isSubmitting) "Sending..." else "Send Code",
            enabled = !uiState.isSubmitting,
            bgColor = Primary,
            disabledBgColor = DisableButton,
            contentColor = TextPrimaryLight,
            disabledContentColor = TextSecondary,
            modifier = Modifier.padding(vertical = 30.dp)
        ) {
            onSubmit()
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun ForgotPasswordHeader() {
    Text(
        text = "Forgot Password",
        style = MaterialTheme.typography.headlineLarge,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = "Enter your email",
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary
    )
}

@Composable
private fun ForgotPasswordForm(
    email: String,
    onEmailChange: (String) -> Unit
) {
    Text(
        text = "Email",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))
    EmailInput(
        email = email,
        onEmailChange = onEmailChange
    )
}

@Composable
private fun EmailInput(
    email: String,
    onEmailChange: (String) -> Unit
) {
    val showCheck = email.isNotBlank()

    BaseInputText(
        value = email,
        onValueChange = onEmailChange,
        placeholder = "Enter your email",
        leadingIcon = Icons.Outlined.Email,
        trailingIcon = if (showCheck) Icons.Outlined.CheckCircle else null,
        showLeadingIcon = true,
        showTrailingIcon = showCheck,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        textColor = TextPrimary,
        placeholderColor = TextSecondary,
        focusedBorderColor = Primary,
        unfocusedBorderColor = TextSecondary.copy(alpha = 0.25f),
        backgroundColor = InputBackground,
        leadingIconColor = TextSecondary,
        trailingIconColor = Primary,
        trailingIconFocusedColor = Primary,
        trailingIconDisabledColor = TextSecondary,
        modifier = Modifier.fillMaxWidth()
    )
}

