package com.minhdk.wefashion.presentation.ui.screen.cart.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.domain.data.cart.DtoCartItem
import com.minhdk.wefashion.domain.data.product.DtoColor
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.screen.cart.main.CartViewModel
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaymentScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit = {},
    selectedAddress: DtoAddress? = null
) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val items = uiState.cart?.items.orEmpty()
    val subtotal = items.sumOf { it.price * it.quantity }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PaymentTopBar(onBack = onBack)

            AddressSection(address = selectedAddress, onEdit = {})

            SectionTitle(text = "Products (${items.size})")

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = items, key = { it.sku }) { item ->
                    PaymentProductRow(item = item)
                }
            }

            TotalRow(total = subtotal)

            BaseButtonBox(
                txt = "Checkout Now",
                bgColor = Primary,
                contentColor = TextPrimaryLight
            ) { }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun PaymentTopBar(
    onBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .clickable { onBack() }
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Payment",
            style = MaterialTheme.typography.titleMedium,
            color = Black
        )
    }
}

@Composable
private fun AddressSection(
    address: DtoAddress? = null,
    onEdit: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Address",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Edit",
                style = MaterialTheme.typography.bodySmall,
                color = Primary,
                modifier = Modifier.clickable { onEdit() }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if(address == null) return

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(InputBackground),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE85652))
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = address.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatAddressLine(address),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        color = Black
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PaymentProductRow(
    item: DtoCartItem
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        GlideImage(
            model = item.productImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(InputBackground)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.productName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Color: ${formatColorLabel(item.color)}",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatVnd(item.price),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )
    }
}

@Composable
private fun TotalRow(total: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Total amount",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = formatVnd(total),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )
    }
}

private fun formatAddressLine(address: DtoAddress): String {
    val parts = listOf(address.detail, address.ward, address.district, address.city)
        .filter { it.isNotBlank() }
    return parts.joinToString(", ")
}

private fun formatColorLabel(color: DtoColor): String {
    val rgb = color.toColor() ?: return color.rgb
    return String.format("#%02X%02X%02X", rgb[0], rgb[1], rgb[2])
}

private fun formatVnd(value: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    return "${formatter.format(value)} VND"
}
