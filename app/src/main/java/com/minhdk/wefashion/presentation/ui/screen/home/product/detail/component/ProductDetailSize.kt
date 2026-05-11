package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.domain.data.product.DtoSize
import com.minhdk.wefashion.presentation.ui.custom.GradientBorderBox
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Composable
fun ProductDetailSize(
    sizes: List<DtoSize>,
    isSelected: (DtoSize) -> Boolean,
    onClick: (DtoSize) -> Unit
) {
    if (sizes.isEmpty()) return

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = sizes, key = {it.id}) { size ->
            Box(
                modifier = Modifier.clickable { onClick(size) }
            ) {
                StateSizeBox(
                    size = size,
                    isSelected = isSelected(size)
                )
            }
        }
    }
}

@Composable
private fun StateSizeBox(
    size: DtoSize,
    isSelected: Boolean = true
) {

    if(isSelected) {
        GradientBorderBox(
            cornerRadius = 10.dp,
            strokeWidth = 2.dp,
            borderColors = listOf(Primary, Color.Transparent),
            modifier = Modifier.height(40.dp)
        ) {
            Text(
                text = size.name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .height(38.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Center)
            )
        }
        return
    }

    Text(
        text = size.name,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
            .height(40.dp)
            .border(1.dp, color = TextSecondary, rounded(10))
            .padding(horizontal = 8.dp)
    )
}