package com.minhdk.wefashion.presentation.ui.screen.order.list_order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.screen.order.list_order.component.OrderItem
import com.minhdk.wefashion.presentation.ui.navigation.Order
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.rounded

@Composable
fun ListOrderScreen(
    contentPadding: PaddingValues,
    onNavigate: (Order) -> Unit
) {
    val viewModel: ListOrderViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.onIntent(ListOrderIntent.LoadOrders)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            OrderTopBar()

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items = uiState.orders, key = { it.id }) { order ->
                    OrderItem(
                        order = order,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = rounded(16))
                    )
                }

                if (uiState.orders.isEmpty() && !uiState.isLoading) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp)
                        ) {
                            Text(
                                text = "No orders yet",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Black
                            )
                        }
                    }
                }
            }
        }

        if (uiState.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black
                )
            }
        }
    }
}

@Composable
private fun OrderTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = "My Order",
            style = MaterialTheme.typography.titleMedium,
            color = Black,
            modifier = Modifier.weight(1f)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .background(InputBackground, rounded(18))
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_cart),
                contentDescription = null,
                tint = Black,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}