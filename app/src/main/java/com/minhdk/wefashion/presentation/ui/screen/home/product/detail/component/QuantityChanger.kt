package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Composable
fun ProductScreenDetailQuantityChanger(
    quantity: Int,
    onClickMinus: () -> Unit = {},
    onClickPlus: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Choose amount:",
            color = Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f).padding(end = 15.dp)
        )

        QuantityChanger(
            quantity = quantity,
            onClickMinus = onClickMinus,
            onClickPlus = onClickPlus
        )
    }
}

// Modifier.width(90.dp).height(30.dp)
@Composable
fun QuantityChanger(
    modifier: Modifier = Modifier,
    quantity: Int,
    onClickMinus: () -> Unit = {},
    onClickPlus: () -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
                .clip(rounded(30))
                .background(color = DisableButton)
                .padding(3.dp)
        ) {
            QuantityButton(
                size = 24.dp,
                icon = R.drawable.ic_minus,
                bgColor = Color.White,
                iconColor = Black,
            ) {
                onClickMinus()
            }

            Text(
                text = quantity.toString(),
                color = Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 11.dp)
            )

            QuantityButton(
                size = 24.dp,
                icon = R.drawable.ic_plus,
                bgColor = Black,
                iconColor = Color.White,
            ) {
                onClickPlus()
            }
        }
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
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(rounded(size.value.toInt() / 2 + 1))
            .background(color = bgColor)
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