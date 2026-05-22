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
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.order.DtoOrder
import com.minhdk.wefashion.presentation.ui.custom.GradientBorderBox
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.Canceled
import com.minhdk.wefashion.presentation.ui.theme.InProgress
import com.minhdk.wefashion.presentation.ui.theme.Succeed
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Preview
@Composable
fun OrderItemPreview() {
    OrderItem(
        order = DtoOrder(
            id = 15,
            discount = 10000,
            shippingFee = 20000,
            totalPrice = 3768000,
            orderState = 2,
            shippingState = -1,
            createdAt = "2026-04-29T22:46:10.244461+07:00",
            userId = 12,
            addressId = 3,
            paymentId = 1,
            shipperId = 8,
            productAmount = 2
        )
    )
}

@Composable
fun OrderItem(
    order: DtoOrder,
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
            OrderInfo(
                imageUrl = null,
                title = "Order #${order.id}",
                date = formatOrderDate(order.createdAt),
                quantity = order.productAmount,
                state = order.orderState.toOrderState(),
                total = order.totalPrice
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OrderInfo(
    imageUrl: String?,
    title: String,
    date: String,
    quantity: Int,
    state: OrderState,
    total: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val model = if (imageUrl.isNullOrBlank()) R.drawable.ic_order_active else imageUrl
        GlideImage(
            model = model,
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black,
                    modifier = Modifier.weight(1f)
                )

                val mColor = state.toColor()

                GradientBorderBox(
                    cornerRadius = 8.dp,
                    strokeWidth = 2.dp,
                    borderColors = listOf(mColor, Color.Transparent),
                    modifier = Modifier.width(90.dp).height(30.dp)
                ) {
                    Text(
                        text = state.value,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium,
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
                        style = MaterialTheme.typography.labelMedium,
                        color = Black
                    )

                    Text(
                        text = "Quantity: $quantity",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium,
                        color = Black
                    )
                }

                Text(
                    text = formatVnd(total),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

enum class OrderState(val value: String) {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    SHIPPING("Shipping"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled")
}

private fun Int.toOrderState(): OrderState {
    return when (this) {
        1 -> OrderState.CONFIRMED
        2 -> OrderState.SHIPPING
        3 -> OrderState.COMPLETED
        4 -> OrderState.CANCELLED
        else -> OrderState.PENDING
    }
}

private fun OrderState.toColor(): Color {
    return when (this) {
        OrderState.COMPLETED -> Succeed
        OrderState.CANCELLED -> Canceled
        OrderState.PENDING, OrderState.CONFIRMED, OrderState.SHIPPING -> InProgress
    }
}

private fun formatOrderDate(raw: String): String {
    val datePart = raw.split("T").firstOrNull() ?: return raw
    val parts = datePart.split("-")
    return if (parts.size == 3) "${parts[2]}-${parts[1]}-${parts[0]}" else datePart
}

private fun formatVnd(value: Int): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale("vi", "VN"))
    return "${formatter.format(value)} VND"
}