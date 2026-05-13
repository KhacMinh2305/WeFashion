package com.minhdk.wefashion.presentation.ui.screen.order.list_order.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.presentation.ui.custom.GradientBorderBox
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.Canceled
import com.minhdk.wefashion.presentation.ui.theme.InProgress
import com.minhdk.wefashion.presentation.ui.theme.Succeed
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Preview
@Composable
fun OrderItem(
    modifier: Modifier = Modifier.background(color = Color.White)
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            OrderInfo("https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg",
                "The order of King Midas aka Doan Khac Minh",
                "23-01-2003",
                2,
                state = OrderState.IN_PROGRESS,
                23.01f)
            OrderButtons({}, {})
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OrderInfo(
    imageUrl: String,
    title: String,
    date: String,
    quantity: Int,
    state: OrderState,
    total: Float
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        GlideImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).clip(rounded(12))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = Black,
                    modifier = Modifier.weight(1f)
                )

                val mColor = when(state) {
                    OrderState.IN_PROGRESS -> InProgress
                    OrderState.CANCELED -> Canceled
                    OrderState.SUCCEED -> Succeed
                }

                GradientBorderBox(
                    cornerRadius = 8.dp,
                    strokeWidth = 2.dp,
                    borderColors = listOf(mColor, Color.Transparent),
                    modifier = Modifier.width(80.dp).height(30.dp)
                ) {
                    Text(
                        text = state.value,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = mColor,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Row (verticalAlignment = Alignment.CenterVertically) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Date: $date",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Black
                    )

                    Text(
                        text = "Quantity: $quantity",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Black
                    )
                }

                Text(
                    text = "$$total",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = Black,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}


@Composable
fun OrderButtons(
    onClickDetail: () -> Unit = {},
    action2: () -> Unit = {}
) {

}

enum class OrderState(val value: String) {
    IN_PROGRESS("In progress"), CANCELED("Canceled"), SUCCEED("Succeed")
}