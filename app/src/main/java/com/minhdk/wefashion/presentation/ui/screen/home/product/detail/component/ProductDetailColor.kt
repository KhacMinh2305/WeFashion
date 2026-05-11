package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.domain.data.product.DtoColor
import com.minhdk.wefashion.presentation.ui.custom.GradientBorderBox
import com.minhdk.wefashion.presentation.ui.theme.Primary

@Composable
fun ProductDetailColor(
    colors: List<DtoColor>,
    isSelected: (DtoColor) -> Boolean,
    onClick: (DtoColor) -> Unit
) {
    if (colors.isEmpty()) return

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = colors, key = { it.id }) { color ->
            Box(
                modifier = Modifier.clickable { onClick(color) }
            ) {
                StateColorBox(
                    color = color,
                    isSelected = isSelected(color)
                )
            }
        }
    }
}

@Composable
private fun StateColorBox(
    color: DtoColor = DtoColor(0, "23,01,23"),
    isSelected: Boolean = true
) {

    val rgbs = color.toColor()?.map { it.toFloat() } ?: return

    if(isSelected) {
        GradientBorderBox(
            cornerRadius = 20.dp,
            strokeWidth = 2.dp,
            borderColors = listOf(Primary, Color.Transparent),
            modifier = Modifier.size(40.dp).clip(CircleShape)
        ) {
            ColorBox(rgbs, 36)
        }
        return
    }

    ColorBox(rgbs, 40)
}

@Composable
private fun ColorBox(
    rgbs: List<Float>,
    size: Int
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(
                Brush.linearGradient(
                    colorStops = arrayOf(
                        rgbs[0] to Color.Red,
                        rgbs[1] to Color.Yellow,
                        rgbs[2] to Color.Blue
                    )
                )
            )
    )
}