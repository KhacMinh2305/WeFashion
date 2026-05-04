package com.minhdk.wefashion.presentation.ui.screen.authentication.verification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.screen.authentication.verification.VerificationUiState
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import com.minhdk.wefashion.presentation.ui.theme.rounded_16

@Composable
fun VerificationContent(
    uiState: VerificationUiState,
    email: String,
    onCodeChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onResend: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val canResend = uiState.resendRemainingSeconds == 0
    val resendText = if (canResend) {
        "Resend"
    } else {
        "Resend (${uiState.resendRemainingSeconds}s)"
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(Background)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        VerificationTopBar(onBack = onBack)
        Spacer(modifier = Modifier.height(20.dp))
        VerificationHeader(email = email)
        Spacer(modifier = Modifier.height(24.dp))
        VerificationCodeInput(
            code = uiState.code,
            onCodeChange = onCodeChange
        )
        Spacer(modifier = Modifier.height(28.dp))
        BaseButtonBox(
            txt = if (uiState.isSubmitting) "Submitting..." else "Submit",
            enabled = !uiState.isSubmitting,
            bgColor = Primary,
            disabledBgColor = DisableButton,
            contentColor = TextPrimaryLight,
            disabledContentColor = TextSecondary,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            onSubmit()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ResendSection(
            text = resendText,
            enabled = canResend,
            onResend = onResend
        )
    }
}

@Composable
private fun VerificationTopBar(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = onBack) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Back"
            )
        }
        Text(
            text = "Verification",
            style = MaterialTheme.typography.titleMedium,
            color = TextPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun VerificationHeader(email: String) {
    VerificationIcon()
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Verification Code",
        style = MaterialTheme.typography.headlineSmall,
        color = TextPrimary,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "We have to sent the code verification to",
        style = MaterialTheme.typography.bodySmall,
        color = TextSecondary,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = email.ifBlank { "your email" },
        style = MaterialTheme.typography.bodyMedium,
        color = TextPrimary,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun VerificationIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Primary.copy(alpha = 0.12f))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Primary)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_verified),
                    contentDescription = "Verified",
                    tint = TextPrimaryLight
                )
            }
        }
    }
}

@Composable
private fun VerificationCodeInput(
    code: String,
    onCodeChange: (String) -> Unit,
    length: Int = 4
) {
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        value = code,
        onValueChange = onCodeChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.titleLarge.copy(color = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .clickable { focusRequester.requestFocus() },
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                innerTextField()
                OtpDigitRow(code = code, length = length)
            }
        }
    )
}

@Composable
private fun OtpDigitRow(code: String, length: Int) {
    val activeIndex = if (code.length < length) code.length else length - 1
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(length) { index ->
            val value = code.getOrNull(index)?.toString() ?: ""
            OtpDigitBox(
                value = value,
                focused = index == activeIndex
            )
        }
    }
}

@Composable
private fun OtpDigitBox(
    value: String,
    focused: Boolean,
    size: Dp = 56.dp
) {
    val borderColor = if (focused) Primary else TextSecondary.copy(alpha = 0.25f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(rounded_16)
            .background(TextPrimaryLight)
            .border(width = 1.dp, color = borderColor, shape = rounded_16)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ResendSection(
    text: String,
    enabled: Boolean,
    onResend: () -> Unit
) {
    val actionColor = if (enabled) Primary else TextSecondary
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Didn't receive the code?",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = actionColor,
            modifier = Modifier.clickable(enabled = enabled) { onResend() }
        )
    }
}
