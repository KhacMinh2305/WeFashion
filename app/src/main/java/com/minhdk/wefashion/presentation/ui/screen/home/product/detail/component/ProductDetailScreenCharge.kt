package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun ProductDetailScreenCharge(
    price: Float,
    onClickAddToCart: () -> Unit = {}
) {
    Row(
       modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = "Price",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start,
                color = TextSecondary,
                modifier = Modifier.wrapContentWidth()
            )

            Text(
                text = "$price VND",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Black,
                modifier = Modifier.wrapContentWidth()
            )
        }

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.weight(1f)
        ) {
            BaseButtonBox(
                txt = "Add to cart",
                bgColor = Primary,
                disabledBgColor = DisableButton,
                contentColor = TextPrimaryLight,
                disabledContentColor = TextSecondary,
                verticalPadding = 10.dp,
                modifier = Modifier.width(170.dp).height(40.dp)
            ) {
                onClickAddToCart()
            }
        }
    }
}