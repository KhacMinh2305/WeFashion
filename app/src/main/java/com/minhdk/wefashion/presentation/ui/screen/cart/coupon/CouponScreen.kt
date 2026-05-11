package com.minhdk.wefashion.presentation.ui.screen.cart.coupon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.Locale

@Composable
fun CouponScreen(
    contentPadding: PaddingValues,
    orderTotal: Int,
    onSelectCoupon: (DtoCoupon) -> Unit,
    onBack: () -> Unit
) {
    val viewModel: CouponViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Coupon",
                style = MaterialTheme.typography.titleMedium,
                color = Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Back",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.clickable { onBack() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        when {
            uiState.isLoading -> {
                Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
            uiState.errorMessage != null -> {
                Text(text = uiState.errorMessage, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
            uiState.coupons.isEmpty() -> {
                Text(text = "Không có coupon", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.coupons, key = { it.id }) { coupon ->
                        val eligible = isCouponEligible(coupon, orderTotal)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(if (eligible) 1f else 0.6f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .clickable(enabled = eligible) { onSelectCoupon(coupon) }
                                .padding(12.dp)
                        ) {
                            CouponInfoRow(label = "Name", value = coupon.name ?: "")
                            CouponInfoRow(label = "Discount", value = formatDiscount(coupon))
                            CouponInfoRow(label = "Discount Type", value = coupon.discountType ?: "")
                            CouponInfoRow(label = "Amount", value = coupon.amount.toString())
                            CouponInfoRow(label = "Created", value = coupon.createdAt ?: "")
                            CouponInfoRow(label = "Expired", value = coupon.expiredAt ?: "")
                            CouponInfoRow(label = "Max Discount", value = formatVnd(coupon.maxDiscount))
                            CouponInfoRow(label = "Min Order", value = formatVnd(coupon.minOrderValue))

                            if (!eligible) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Không đủ điều kiện",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CouponInfoRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Black,
            modifier = Modifier.weight(0.6f)
        )
    }
}

private fun isCouponEligible(coupon: DtoCoupon, orderTotal: Int): Boolean {
    val hasName = !coupon.name.isNullOrBlank()
    if (!hasName) return false
    if (orderTotal < coupon.minOrderValue) return false
    if (isExpired(coupon.expiredAt)) return false
    return true
}

private fun isExpired(expiredAt: String?): Boolean {
    if (expiredAt.isNullOrBlank()) return false
    val now = Instant.now()
    val instant = runCatching { Instant.parse(expiredAt) }.getOrNull()
        ?: runCatching { OffsetDateTime.parse(expiredAt).toInstant() }.getOrNull()
        ?: runCatching { LocalDateTime.parse(expiredAt).atZone(ZoneId.systemDefault()).toInstant() }.getOrNull()
    return instant?.isBefore(now) ?: false
}

private fun formatDiscount(coupon: DtoCoupon): String {
    val formattedValue = formatNumber(coupon.discountValue)
    return when (coupon.discountType?.trim()) {
        "%" -> "${coupon.discountValue}%"
        "VND" -> formattedValue
        else -> formattedValue
    }
}

private fun formatNumber(value: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    return formatter.format(value)
}

private fun formatVnd(value: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    return "${formatter.format(value)} VND"
}