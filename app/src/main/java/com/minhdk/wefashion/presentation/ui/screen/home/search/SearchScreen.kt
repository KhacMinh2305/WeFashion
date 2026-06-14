package com.minhdk.wefashion.presentation.ui.screen.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.navigation.Home
import com.minhdk.wefashion.presentation.ui.screen.home.search.component.SearchProductItem
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimary
import com.minhdk.wefashion.util.helper.logD
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    contentPadding: PaddingValues,
    onNavigate: (Home) -> Unit
) {
    val viewmodel: SearchViewModel = hiltViewModel()
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        snapshotFlow {
            uiState.value.query
        }.debounce(1000L)
            .distinctUntilChanged()
            .collect {
                viewmodel.handle(SearchIntent.Search(it))
            }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(color = Background)
            .padding(contentPadding)
    ) {

        item {
            Header(
                query = uiState.value.query,
                {
                    onNavigate(Home.Back)
                }, {
                    viewmodel.handle(SearchIntent.Typing(it))
                }
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }

        if(!uiState.value.isLoading) {
            items(items = uiState.value.products, key = { it.id }) { item ->
                SearchProductItem(item) {
                    onNavigate(Home.ProductDetail(item.id))
                }
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        } else {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.LightGray,
                        trackColor = Primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun Header(
    query: String?,
    onClickBack: () -> Unit = {},
    onTextChanged: (String) -> Unit = {}
) {

    Row(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(50.dp).clickable {
                onClickBack()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        BaseInputText(
            value = query ?: "",
            onValueChange = onTextChanged,
            textColor = TextPrimary,
            placeholderColor = TextPrimary,
            focusedBorderColor = Primary,
            unfocusedBorderColor = TextPrimary,
            backgroundColor = Color.Transparent,
            leadingIconColor = Color.Transparent,
            trailingIconColor = Color.Transparent,
            trailingIconFocusedColor = Color.Transparent,
            trailingIconDisabledColor =Color.Transparent,
            modifier = Modifier.weight(1f).height(50.dp),
        )
    }
}