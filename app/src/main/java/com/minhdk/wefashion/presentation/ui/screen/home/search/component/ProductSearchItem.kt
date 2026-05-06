package com.minhdk.wefashion.presentation.ui.screen.home.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.rounded

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun SearchProductItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.White, rounded(15))
    ) {
        Row (
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            GlideImage(
                model = "https://cdn-media.sforum.vn/storage/app/media/anh-dep-15.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(rounded(15))
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {

                Text(
                    text = "Product mame",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Product mame",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}