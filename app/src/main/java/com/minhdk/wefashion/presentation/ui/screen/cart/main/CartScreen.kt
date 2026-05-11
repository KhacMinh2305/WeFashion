package com.minhdk.wefashion.presentation.ui.screen.cart.main

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.cart.DtoCartItem
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import java.text.NumberFormat
import java.util.Locale
import android.widget.Toast
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Brush
import com.minhdk.wefashion.presentation.ui.theme.rounded

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartScreen(
    contentPadding: PaddingValues,
    selectedCouponName: String,
    onConsumeSelectedCoupon: () -> Unit,
    onOpenCoupon: (Int) -> Unit
) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onIntent(CartIntent.LoadCart)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            if (effect is CartEffect.ShowToast) {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(selectedCouponName) {
        if (selectedCouponName.isNotBlank()) {
            viewModel.onIntent(CartIntent.PromoCodeChanged(selectedCouponName))
            viewModel.onIntent(CartIntent.ApplyPromo)
            onConsumeSelectedCoupon()
        }
    }

    val items = uiState.cart?.items.orEmpty()
    val selectedItems = items.filter { uiState.selectedSkus.contains(it.sku) }
    val subtotal = selectedItems.sumOf { it.price * it.quantity }
    val shippingFee = if (subtotal > 0) 6000 else 0
    val totalBeforeDiscount = subtotal + shippingFee
    val total = (totalBeforeDiscount - uiState.discountValue).coerceAtLeast(0)
    val promoDisplay = uiState.appliedCoupon?.let { formatCouponDisplay(it) } ?: uiState.promoCode

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CartTopBar()

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items = items, key = {it.sku} ) { item ->
                    CartItemRow(
                        item = item,
                        isSelected = uiState.selectedSkus.contains(item.sku),
                        onToggle = { viewModel.onIntent(CartIntent.ToggleSelectSku(item.sku)) },
                        onMinus = { viewModel.onIntent(CartIntent.UpdateSkuQuantity(item.sku, "minus", 1)) },
                        onPlus = { viewModel.onIntent(CartIntent.UpdateSkuQuantity(item.sku, "plus", 1)) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth().height(1.dp).background(color = TextSecondary)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(160.dp))
                }
            }
        }

        CartSummaryPanel(
            promoCode = promoDisplay,
            subtotal = subtotal,
            shippingFee = shippingFee,
            discount = uiState.discountValue,
            total = total,
            onPromoChanged = { viewModel.onIntent(CartIntent.PromoCodeChanged(it)) },
            onOpenCoupon = { onOpenCoupon(totalBeforeDiscount) },
            hasAppliedCoupon = uiState.appliedCoupon != null,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun CartTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.titleMedium,
            color = Black,
            modifier = Modifier.weight(1f).padding(start = 12.dp)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CartItemRow(
    item: DtoCartItem,
    isSelected: Boolean,
    onToggle: () -> Unit,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {

    val rgbs = item.color.toColor()?.map { it.toFloat() } ?: return

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val checkIcon = if (isSelected) R.drawable.ic_check_item else R.drawable.ic_uncheck_item
        androidx.compose.foundation.Image(
            painter = painterResource(checkIcon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onToggle() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        GlideImage(
            model = item.productImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.productName,
                style = MaterialTheme.typography.bodyMedium,
                color = Black,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Color: ",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )

                Box(
                    modifier = Modifier
                        .size(10.dp)
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

            Spacer(modifier = Modifier.height(8.dp))

            QuantityControl(
                quantity = item.quantity,
                onMinus = onMinus,
                onPlus = onPlus
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatVnd(item.price),
            style = MaterialTheme.typography.titleSmall,
            color = Black
        )
    }
}

@Composable
private fun QuantityControl(
    quantity: Int,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(InputBackground, RoundedCornerShape(20.dp))
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        QuantityButton(icon = R.drawable.ic_minus, bgColor = Color.White, tint = Black, onClick = onMinus)
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Black,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        QuantityButton(icon = R.drawable.ic_plus, bgColor = Black, tint = Color.White, onClick = onPlus)
    }
}

@Composable
private fun QuantityButton(
    icon: Int,
    bgColor: Color,
    tint: Color,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(bgColor)
            .clickable { onClick() }
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
private fun CartSummaryPanel(
    promoCode: String,
    subtotal: Int,
    shippingFee: Int,
    discount: Int,
    total: Int,
    onPromoChanged: (String) -> Unit,
    onOpenCoupon: () -> Unit,
    hasAppliedCoupon: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        PromoInputRow(
            value = promoCode,
            hasAppliedCoupon = hasAppliedCoupon,
            onValueChange = onPromoChanged,
            onOpen = onOpenCoupon
        )

        Spacer(modifier = Modifier.height(16.dp))

        SummaryRow(label = "Subtotal", value = formatVnd(subtotal))
        SummaryRow(label = "Shipping", value = formatVnd(shippingFee))
        SummaryRow(label = "Discount", value = "-${formatVnd(discount)}")

        Spacer(modifier = Modifier.height(8.dp))

        SummaryRow(
            label = "Total amount",
            value = formatVnd(total),
            isTotal = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseButtonBox(
            txt = "Checkout",
            bgColor = Primary,
            contentColor = TextPrimaryLight
        ) { }
    }
}

@Composable
private fun PromoInputRow(
    value: String,
    hasAppliedCoupon: Boolean,
    onValueChange: (String) -> Unit,
    onOpen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(InputBackground, RoundedCornerShape(14.dp))
            .clickable { onOpen() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(R.drawable.ic_ticket),
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(modifier = Modifier.weight(1f)) {
            if (hasAppliedCoupon) {
                Text(
                    text = value,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black
                )
            } else {
                Text(
                    text = "Chọn coupon",
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isTotal) Black else TextSecondary,
            fontWeight = if (isTotal) FontWeight.SemiBold else FontWeight.Normal
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isTotal) Black else TextSecondary,
            fontWeight = if (isTotal) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
private fun IconCircleButton(
    icon: Int,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() }
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Black,
            modifier = Modifier.size(18.dp)
        )
    }
}

private fun formatCouponDisplay(coupon: DtoCoupon): String {
    val code = coupon.name ?: return ""
    val discountText = when (coupon.discountType?.lowercase()) {
        "percent", "percentage" -> "${coupon.discountValue}%"
        else -> formatVnd(coupon.discountValue)
    }
    return "$code - $discountText"
}

private fun formatVnd(value: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    return "${formatter.format(value)} VND"
}
