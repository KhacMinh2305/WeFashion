package com.minhdk.wefashion.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun BaseInputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

    placeholder: String = "",

    // Leading icon
    leadingIcon: ImageVector? = null,
    showLeadingIcon: Boolean = true,

    // Trailing icon
    trailingIcon: ImageVector? = null,
    showTrailingIcon: Boolean = true,
    trailingEnabled: Boolean = true,
    onTrailingClick: (() -> Unit)? = null,

    // Input
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,

    // Colors
    textColor: Color,
    placeholderColor: Color,
    focusedBorderColor: Color,
    unfocusedBorderColor: Color,
    backgroundColor: Color,
    leadingIconColor: Color,
    trailingIconColor: Color,
    trailingIconFocusedColor: Color,
    trailingIconDisabledColor: Color
) {
    var isFocused by remember { mutableStateOf(true) }

    val borderColor = if (isFocused) focusedBorderColor else unfocusedBorderColor

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .onFocusChanged { isFocused = it.isFocused }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            // Leading Icon
            if (showLeadingIcon && leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = leadingIconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            // TextField + Placeholder
            Box(modifier = Modifier.weight(1f)) {

                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    visualTransformation = visualTransformation,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (showTrailingIcon && trailingIcon != null) {
                Spacer(modifier = Modifier.width(12.dp))

                val tint = if (trailingEnabled) {
                    if(isFocused) trailingIconFocusedColor else trailingIconColor
                } else {
                    trailingIconDisabledColor
                }

                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            enabled = trailingEnabled && onTrailingClick != null
                        ) {
                            onTrailingClick?.invoke()
                        }
                )
            }
        }
    }
}

//BaseInputText(
//value = text,
//onValueChange = { text = it },
//placeholder = "Enter your email",
//leadingIcon = Icons.Default.Preview,
//trailingIcon = Icons.Default.LocalActivity,
//showTrailingIcon = true,
//trailingEnabled = true,
//onTrailingClick = {  },
//
//textColor = Color(0xFFFFFFFF),
//placeholderColor = Color(0xFF6B6B72),
//focusedBorderColor = Color(0xFF5B5BD6),
//unfocusedBorderColor = Color(0xFFE5E7EB),
//backgroundColor = Color.Transparent,
//leadingIconColor = Color(0xFF9CA3AF),
//trailingIconColor = Color(0xFFFFFFFF),
//trailingIconFocusedColor = Color(0xFF5B5BD6),
//trailingIconDisabledColor = Color(0xFF505050)
//)