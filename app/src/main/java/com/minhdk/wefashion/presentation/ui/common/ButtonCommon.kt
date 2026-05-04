package com.minhdk.wefashion.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun BaseButtonBox(
    modifier: Modifier = Modifier,
    txt: String,
    enabled: Boolean = true,
    bgColor: Color,
    disabledBgColor: Color = DisableButton,
    contentColor: Color,
    disabledContentColor: Color = TextSecondary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        BaseButton(
            text = txt,
            enabled = enabled,
            backgroundColor = bgColor,
            disabledBackgroundColor = disabledBgColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor,
            onClick = onClick
        )
    }
}

@Composable
fun BaseButton(
    text: String,
    enabled: Boolean = true,
    // Colors
    backgroundColor: Color,
    disabledBackgroundColor: Color = DisableButton,
    contentColor: Color,
    disabledContentColor: Color = TextSecondary,
    // Shape + style
    cornerRadius: Dp = 24.dp,
    textSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    // Padding
    verticalPadding: Dp = 16.dp,
    onClick: () -> Unit
) {
    val bgColor = if (enabled) backgroundColor else disabledBackgroundColor
    val txtColor = if (enabled) contentColor else disabledContentColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background(bgColor)
            .clickable(enabled = enabled) { onClick() }
            .padding(vertical = verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = txtColor,
            fontSize = textSize,
            fontWeight = fontWeight
        )
    }
}