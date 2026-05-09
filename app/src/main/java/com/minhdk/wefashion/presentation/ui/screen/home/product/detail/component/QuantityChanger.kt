package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Composable
fun QuantityChanger(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        
    }
}

@Composable
private fun QuantityButton(
    size: Dp,
    icon: Int,
    bgColor: Color,
    iconColor: Color,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = bgColor)
            .clip(rounded(size.value.toInt() / 2 + 1))
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(size / 2)
        )
    }
}