package com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}