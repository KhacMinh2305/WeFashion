package com.minhdk.wefashion.presentation.ui.screen.authentication.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.screen.authentication.login.LoginUiState
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    onForgotPassword: () -> Unit,
    onSubmit: () -> Unit,
    onRegister:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(Background)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {

        LoginHeader()

        Spacer(modifier = Modifier.height(24.dp))

        LoginForm(
            uiState = uiState,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onTogglePassword = onTogglePassword,
            onForgotPassword = onForgotPassword
        )

        Spacer(modifier = Modifier.height(28.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f).padding(vertical = 12.dp)
        ) {

            BaseButtonBox (
                txt = if (uiState.isSubmitting) "Submitting..." else "Sign In",
                enabled = !uiState.isSubmitting,
                bgColor = Primary,
                disabledBgColor = DisableButton,
                contentColor = TextPrimaryLight,
                disabledContentColor = TextSecondary
            ) {
                onSubmit()
            }

            Spacer(modifier = Modifier.height(15.dp))

            BaseButtonBox (
                txt = "Register",
                enabled = !uiState.isSubmitting,
                bgColor = Primary,
                disabledBgColor = DisableButton,
                contentColor = TextPrimaryLight,
                disabledContentColor = TextSecondary
            ) {
                onRegister()
            }
        }
    }
}

@Composable
private fun ColumnScope.LoginHeader() {

    Spacer(modifier = Modifier.height(20.dp))

    Image(
        painter = painterResource(R.drawable.logo_inapp),
        contentDescription = "App Icon",
        contentScale = ContentScale.Crop,
        modifier = Modifier.
        fillMaxWidth(0.4f)
            .aspectRatio(1f)
            .align(Alignment.CenterHorizontally)
            .clip(rounded(10))
    )

    Spacer(modifier = Modifier.height(50.dp))

    Text(
        text = "Login Account",
        style = MaterialTheme.typography.headlineSmall,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = "Please login with registered account",
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary
    )
}

@Composable
private fun LoginForm(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    onForgotPassword: () -> Unit
) {
    Text(
        text = "Username",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))
    BaseInputText(
        value = uiState.username,
        onValueChange = onEmailChange,
        placeholder = "Username",
        leadingIcon = Icons.Outlined.Email,
        showLeadingIcon = true,
        showTrailingIcon = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        textColor = TextPrimary,
        placeholderColor = TextSecondary,
        focusedBorderColor = Primary,
        unfocusedBorderColor = TextSecondary.copy(alpha = 0.25f),
        backgroundColor = InputBackground,
        leadingIconColor = TextSecondary,
        trailingIconColor = TextSecondary,
        trailingIconFocusedColor = Primary,
        trailingIconDisabledColor = TextSecondary,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(18.dp))

    Text(
        text = "Password",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))

    val passwordIcon = if (uiState.isPasswordVisible) {
        Icons.Outlined.VisibilityOff
    } else {
        Icons.Outlined.Visibility
    }
    val transformation = if (uiState.isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    BaseInputText(
        value = uiState.password,
        onValueChange = onPasswordChange,
        placeholder = "Password",
        leadingIcon = Icons.Outlined.Lock,
        trailingIcon = passwordIcon,
        showLeadingIcon = true,
        showTrailingIcon = true,
        trailingEnabled = true,
        onTrailingClick = onTogglePassword,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = transformation,
        textColor = TextPrimary,
        placeholderColor = TextSecondary,
        focusedBorderColor = Primary,
        unfocusedBorderColor = TextSecondary.copy(alpha = 0.25f),
        backgroundColor = InputBackground,
        leadingIconColor = TextSecondary,
        trailingIconColor = TextSecondary,
        trailingIconFocusedColor = Primary,
        trailingIconDisabledColor = TextSecondary,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(15.dp))

    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.bodySmall,
            color = Primary,
            modifier = Modifier.clickable { onForgotPassword() }
        )
    }

}
