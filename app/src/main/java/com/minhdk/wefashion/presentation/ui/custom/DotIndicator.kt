package com.minhdk.wefashion.presentation.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    quantity: Int,
    indicatorSize: Dp,
    indicatorSpacing: Dp,
    activeIndicatorColor: Color,
    inactiveIndicatorColor: Color,
    selectedPos: Int
) {

    Canvas(modifier = modifier) {
        val (width, height) = size

        val indicatorSizePx = indicatorSize.toPx()
        val indicatorSpacingPx = indicatorSpacing.toPx()

        val consumedWidth = indicatorSizePx * quantity + indicatorSpacingPx * (quantity - 1)

        var startX = (width - consumedWidth) / 2
        val startY = (height - indicatorSizePx) / 2

        for(i in 0 until quantity) {
            val colorToDraw = if(i == selectedPos) activeIndicatorColor else inactiveIndicatorColor
            val r = indicatorSizePx / 2
            val centerPoint = Offset(startX + r, startY + r)
            drawCircle(color = colorToDraw, radius = r, centerPoint)
            startX += indicatorSizePx + indicatorSpacingPx
        }
    }
}

@Preview
@Composable
fun DotIndicatorPreview() {
    DotIndicator(
        quantity = 3,
        indicatorSize = 12.dp,
        indicatorSpacing = 8.dp,
        activeIndicatorColor = Primary,
        inactiveIndicatorColor = TextSecondary,
        selectedPos = 2,
        modifier = Modifier.fillMaxWidth().height(50.dp)
    )
}