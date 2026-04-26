package com.minhdk.wefashion.presentation.ui.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.util.extension.gradientBorder

@Composable
fun GradientBorderBox(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    strokeWidth: Dp = 2.dp,
    enabled: Boolean = true,
    borderColors: List<Color>,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .then(
                    Modifier.gradientBorder(
                        strokeWidth = strokeWidth, cornerRadius = cornerRadius, colors = borderColors
                    ).padding(strokeWidth).takeIf({ enabled }) ?: Modifier
                )
        ) {
            content()
        }
    }
}