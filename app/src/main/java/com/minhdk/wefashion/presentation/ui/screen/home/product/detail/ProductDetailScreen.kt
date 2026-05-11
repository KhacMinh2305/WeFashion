package com.minhdk.wefashion.presentation.ui.screen.home.product.detail

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.domain.data.product.DtoColor
import com.minhdk.wefashion.domain.data.product.DtoSize
import com.minhdk.wefashion.presentation.ui.navigation.Home
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailColor
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailSize
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailScreenCharge
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductDetailScreenHeader
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductInfo
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductScreenDetailQuantityChanger
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.component.ProductScreenDetailShopInfo
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.roundedTop
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    contentPadding: PaddingValues,
    onNavigate: (Home) -> Unit = {}
) {

    val viewmodel: ProductDetailViewModel = hiltViewModel()
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.effect.collect { effect ->
            if (effect is ProductDetailEffect.ShowToast) {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val productDetail = uiState.productDetail
    val product = productDetail?.product
    val shop = productDetail?.shop
    val skus = productDetail?.sku.orEmpty()
    val price = productDetail?.sku?.firstOrNull()?.price?.toFloat() ?: 0f
    val imageUrl = product?.imageUrl
        ?: "https://cdn.sachhayonline.com/wp-content/uploads/2026/01/cho-meme-hai.jpg"

    var selectedColor by remember { mutableStateOf<DtoColor?>(null) }
    var selectedSize by remember { mutableStateOf<DtoSize?>(null) }

    val allColors = skus.map { it.color }.distinctBy { it.id }
    val allSizes = skus.map { it.size }.distinctBy { it.id }

    val availableColors = if (selectedSize == null) {
        allColors
    } else {
        skus.filter { it.size.id == selectedSize?.id }
            .map { it.color }
            .distinctBy { it.id }
    }

    val availableSizes = if (selectedColor == null) {
        allSizes
    } else {
        skus.filter { it.color.id == selectedColor?.id }
            .map { it.size }
            .distinctBy { it.id }
    }

    LaunchedEffect(availableColors) {
        if (selectedColor != null && availableColors.none { it.id == selectedColor?.id }) {
            selectedColor = null
        }
    }

    LaunchedEffect(availableSizes) {
        if (selectedSize != null && availableSizes.none { it.id == selectedSize?.id }) {
            selectedSize = null
        }
    }

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
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().height(1.dp).background(color = TextSecondary)
                )
                Spacer(modifier = Modifier.height(10.dp))

                ProductScreenDetailQuantityChanger(
                    uiState.quantity,
                    { viewmodel.handle(ProductDetailIntent.DecreaseQuantity) },
                    { viewmodel.handle(ProductDetailIntent.IncreaseQuantity) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().height(1.dp).background(color = TextSecondary)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Color",
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                ProductDetailColor(
                    colors = availableColors,
                    isSelected = { color -> selectedColor?.id == color.id },
                    onClick = { color ->
                        selectedColor = if (selectedColor?.id == color.id) null else color
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().height(1.dp).background(color = TextSecondary)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Size",
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                ProductDetailSize(
                    sizes = availableSizes,
                    isSelected = { size -> selectedSize?.id == size.id },
                    onClick = { size ->
                        selectedSize = if (selectedSize?.id == size.id) null else size
                    }
                )
            }


            item {
                Spacer(modifier = Modifier.height(30.dp))

                ProductDetailScreenCharge(price) {
                    if (selectedColor == null || selectedSize == null) {
                        Toast.makeText(
                            context,
                            "Vui lòng chọn đủ size và color",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@ProductDetailScreenCharge
                    }

                    val selectedSku = skus.firstOrNull {
                        it.color.id == selectedColor?.id && it.size.id == selectedSize?.id
                    }

                    if (selectedSku == null) {
                        Toast.makeText(
                            context,
                            "Không tìm thấy SKU phù hợp",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@ProductDetailScreenCharge
                    }

                    viewmodel.handle(
                        ProductDetailIntent.AddToCart(
                            sku = selectedSku.sku,
                            quantity = uiState.quantity
                        )
                    )
                }
            }

        }

    }
}