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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.util.helper.logD

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    contentPadding: PaddingValues,
    onNavigate: (Home) -> Unit = {}
) {

    val viewmodel: ProductDetailViewModel = hiltViewModel()
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value

    val productDetail = uiState.productDetail
    val product = productDetail?.product
    val shop = productDetail?.shop
    val price = productDetail?.sku?.firstOrNull()?.price?.toFloat() ?: 0f
    val imageUrl = product?.imageUrl
        ?: "https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg"

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize().padding(contentPadding)
    ) {

        GlideImage(
            model = imageUrl,
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
                    title = product?.name ?: "",
                    rating = product?.rating?.toFloat() ?: 0f,
                    description = product?.description ?: ""
                )

                Spacer(modifier = Modifier.height(15.dp))

                ProductScreenDetailShopInfo(
                    avatarUrl = shop?.avatarUrl
                        ?: "https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg",
                    name = shop?.name ?: "",
                    followers = shop?.followers ?: 0
                )
            }

            item {
                Spacer(modifier = Modifier.height(25.dp))

                ProductScreenDetailQuantityChanger(
                    uiState.quantity,
                    { viewmodel.handle(ProductDetailIntent.DecreaseQuantity) },
                    { viewmodel.handle(ProductDetailIntent.IncreaseQuantity) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))

                ProductDetailScreenCharge(price) {
                    viewmodel.handle(ProductDetailIntent.AddToCart)
                }
            }

        }

    }
}