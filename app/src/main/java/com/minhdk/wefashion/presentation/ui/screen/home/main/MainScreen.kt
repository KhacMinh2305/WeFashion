package com.minhdk.wefashion.presentation.ui.screen.home.main

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.coupon.DtoCoupon
import com.minhdk.wefashion.domain.data.product.DtoProduct
import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.presentation.ui.common.StateBox
import com.minhdk.wefashion.presentation.ui.common.UiState
import com.minhdk.wefashion.presentation.ui.custom.DotIndicator
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import com.minhdk.wefashion.presentation.ui.navigation.Home
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

@Composable
fun MainScreen(
    contentPadding: PaddingValues,
    onNavigate: (Home) -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MainContent(
        uiState = uiState,
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        onNavigate(it)
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onNavigate: (Home) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(Background)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            HeaderSection(userState = uiState.user) {
                onNavigate(Home.Search)
            }
        }
        item {
            PromoBannerSection(couponsState = uiState.coupons)
        }
        item {
            ProductGroup(
                title = "Top Rated",
                state = uiState.topRated
            ) {
                onNavigate(Home.ProductDetail(it.id))
            }
        }
        item {
            ProductGroup(
                title = "Best Seller",
                state = uiState.bestSeller
            ) {
                onNavigate(Home.ProductDetail(it.id))
            }
        }
        item {
            ProductGroup(
                title = "People Liked",
                state = uiState.mostLiked
            ) {
                onNavigate(Home.ProductDetail(it.id))
            }
        }
        item {
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
private fun HeaderSection(
    userState: UiState<DtoUser>,
    onClick: () -> Unit
) {
    StateBox(
        state = userState,
        loading = { HeaderContent(name = "Hi", bio = "", avatarUrl = "") },
        success = { user ->
            HeaderContent(
                name = "Hi, " + user.name,
                bio = user.bio,
                avatarUrl = user.avatarUrl
            ) {
                onClick()
            }
        },
        error = { HeaderContent(name = "Hi", bio = "", avatarUrl = "") },
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HeaderContent(
    name: String,
    bio: String,
    avatarUrl: String,
    onClickSearch: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(InputBackground),
            contentAlignment = Alignment.Center
        ) {
            if (avatarUrl.isNotBlank()) {
                GlideImage(
                    model = avatarUrl,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_user_active),
                    contentDescription = "Avatar",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = bio,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(BorderStroke(1.dp, TextSecondary.copy(alpha = 0.15f)), CircleShape)
                .clickable {
                    onClickSearch()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
private fun PromoBannerSection(
    couponsState: UiState<List<DtoCoupon>>
) {
    StateBox(
        state = couponsState,
        loading = { PromoBannerShimmer() },
        success = { coupons ->
            val items = coupons.take(3)
            if (items.isEmpty()) {
                PromoBannerShimmer()
            } else {
                PromoBannerPager(items)
            }
        },
        error = { PromoBannerShimmer() }
    )
}

@Composable
private fun PromoBannerPager(
    coupons: List<DtoCoupon>
) {
    val pagerState = rememberPagerState { coupons.size }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF1F2F8))
            .padding(16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            PromoBannerItem(coupons[page])
        }
        Spacer(modifier = Modifier.height(12.dp))
        DotIndicator(
            quantity = coupons.size,
            indicatorSize = 6.dp,
            indicatorSpacing = 6.dp,
            activeIndicatorColor = Primary,
            inactiveIndicatorColor = TextSecondary.copy(alpha = 0.3f),
            selectedPos = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
    }
}

@Composable
private fun PromoBannerItem(coupon: DtoCoupon) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coupon.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "Promo",
                modifier = Modifier.size(42.dp)
            )
        }
    }
}

@Composable
private fun PromoBannerShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF1F2F8))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(InputBackground)
        )
        Spacer(modifier = Modifier.height(12.dp))
        DotIndicator(
            quantity = 3,
            indicatorSize = 6.dp,
            indicatorSpacing = 6.dp,
            activeIndicatorColor = Primary,
            inactiveIndicatorColor = TextSecondary.copy(alpha = 0.3f),
            selectedPos = 0,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
    }
}

@Composable
private fun ProductGroup(
    title: String,
    state: UiState<List<DtoProduct>>,
    onClickItem: (DtoProduct) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))
        StateBox(
            state = state,
            loading = { ProductListShimmer() },
            success = { products -> ProductList(products) {
                onClickItem(it)
            } },
            error = { ProductList(emptyList()) {} }
        )
    }
}

@Composable
private fun ProductList(
    products: List<DtoProduct>,
    onClickItem: (DtoProduct) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { item ->
            ProductItem(item) {
                onClickItem(item)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProductItem(product: DtoProduct, onClickItem: () -> Unit) {
    Column(
        modifier = Modifier.width(150.dp).clickable {
            onClickItem()
        }
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(InputBackground)
        ) {
            GlideImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.description.ifBlank { "WeFashion" },
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$${product.likedAmount}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
    }
}

@Composable
private fun ProductListShimmer() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(10) {
            ShimmerProductItem()
        }
    }
}

@Composable
private fun ShimmerProductItem() {
    val shimmerColors = listOf(
        Color(0xFFECEEF3),
        Color(0xFFF5F6FA),
        Color(0xFFECEEF3)
    )
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateX by transition.animateFloat(
        initialValue = 0f,
        targetValue = 320f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerX"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateX - 200f, 0f),
        end = Offset(translateX, 200f)
    )

    Column(
        modifier = Modifier.width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(14.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .height(12.dp)
                .width(90.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .height(14.dp)
                .width(70.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
    }
}
