package com.minhdk.wefashion.presentation.ui.screen.authentication.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.screen.authentication.register.RegisterUiState
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox

@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
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

        Spacer(modifier = Modifier.height(24.dp))

        RegisterHeader()
        Spacer(modifier = Modifier.height(24.dp))
        RegisterForm(
            uiState = uiState,
            onUsernameChange = onUsernameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onTogglePassword = onTogglePassword
        )
        Spacer(modifier = Modifier.height(28.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            BaseButtonBox(
                txt = if (uiState.isSubmitting) "Submitting..." else "Create Account",
                enabled = !uiState.isSubmitting,
                bgColor = Primary,
                disabledBgColor = DisableButton,
                contentColor = TextPrimaryLight,
                disabledContentColor = TextSecondary
            ) {
                onSubmit()
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun RegisterHeader() {
    Text(
        text = "Create Account",
        style = MaterialTheme.typography.headlineSmall,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = "Start learning with create your account",
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary
    )
}

@Composable
private fun RegisterForm(
    uiState: RegisterUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit
) {
    UsernameField(
        value = uiState.username,
        onValueChange = onUsernameChange
    )
    Spacer(modifier = Modifier.height(18.dp))
    EmailField(
        value = uiState.emailOrPhone,
        onValueChange = onEmailChange
    )
    Spacer(modifier = Modifier.height(18.dp))
    PasswordField(
        value = uiState.password,
        isVisible = uiState.isPasswordVisible,
        onValueChange = onPasswordChange,
        onTogglePassword = onTogglePassword
    )
}

@Composable
private fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = "Username",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))
    BaseInputText(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Username",
        leadingIcon = Icons.Outlined.Person,
        showLeadingIcon = true,
        showTrailingIcon = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
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
}

@Composable
private fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = "Email",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))
    BaseInputText(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Email",
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
}

@Composable
private fun PasswordField(
    value: String,
    isVisible: Boolean,
    onValueChange: (String) -> Unit,
    onTogglePassword: () -> Unit
) {
    Text(
        text = "Password",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))

    val passwordIcon = if (isVisible) {
        Icons.Outlined.VisibilityOff
    } else {
        Icons.Outlined.Visibility
    }
    val transformation = if (isVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    BaseInputText(
        value = value,
        onValueChange = onValueChange,
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
}
