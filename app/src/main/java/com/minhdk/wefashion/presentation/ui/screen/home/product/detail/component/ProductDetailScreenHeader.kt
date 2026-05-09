package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.theme.Black

@Composable
fun ProductDetailScreenHeader(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClickBack) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                tint = Black,
                contentDescription = "Back"
            )
        }
        Text(
            text = "Product Detail",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun ProductDetailScreenHeaderPreview() {
    ProductDetailScreenHeader(
        modifier = Modifier.fillMaxWidth()
    )
}