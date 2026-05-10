package com.minhdk.wefashion.presentation.ui.screen.home.product.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.presentation.ui.navigation.Home
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailScreenCharge
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailScreenHeader
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductInfo
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductScreenDetailQuantityChanger
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductScreenDetailShopInfo
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.roundedTop

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    contentPadding: PaddingValues,
    onNavigate: (Home) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize().padding(contentPadding)
    ) {

        GlideImage(
            model = "https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .align(Alignment.TopCenter)
        )

        ProductDetailScreenHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            onNavigate(Home.Back)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(color = Background, shape = roundedTop(20))
                .padding(15.dp)
                .align(Alignment.BottomCenter)
        ) {

            item {
                ProductInfo(
                    title = "Doan Khac Minh",
                    rating = 4.5f,
                    description = "Lorem ipsum là một đoạn văn bản giả hoặc văn bản giữ chỗ thường được sử dụng trong thiết kế đồ họa, xuất bản và phát triển web."
                )

                Spacer(modifier = Modifier.height(15.dp))

                ProductScreenDetailShopInfo(
                    avatarUrl = "https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg",
                    name = "Doan Khac Minh",
                    followers = 1000
                )
            }

            item {
                Spacer(modifier = Modifier.height(25.dp))

                ProductScreenDetailQuantityChanger(
                    1, {}, {}
                )
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))

                ProductDetailScreenCharge(
                    23.01f
                ) {

                }
            }

        }

    }
}