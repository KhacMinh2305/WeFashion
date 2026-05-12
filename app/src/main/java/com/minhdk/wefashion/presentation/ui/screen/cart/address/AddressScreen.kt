package com.minhdk.wefashion.presentation.ui.screen.cart.address

import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Black
import com.minhdk.wefashion.presentation.ui.theme.InputBackground
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun SelectAddressScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
    createAddress: () -> Unit,
    onConfirm: (SelectedAddress) -> Unit,
    createdAddressId: Int? = null,
    onConsumeCreatedAddress: () -> Unit = {}
) {
    val viewModel: SelectAddressViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            if (effect is AddressEffect.ConfirmSelection) {
                onConfirm(effect.address.toSelectedAddress())
            }
        }
    }

    LaunchedEffect(createdAddressId) {
        val id = createdAddressId ?: return@LaunchedEffect
        viewModel.handle(AddressIntent.Created(id))
        onConsumeCreatedAddress()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AddressTopBar(onBack = onBack)

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Choose your location",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Let's find your unforgettable event. Choose a location below to get started.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            LocationSearchBox {
                createAddress()
            }

            Text(
                text = "Select location",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.addresses, key = { it.id }) { item ->
                    AddressRow(
                        address = item,
                        isSelected = uiState.selectedId == item.id,
                        onSelect = { viewModel.handle(AddressIntent.Select(item.id)) }
                    )
                }
            }

            BaseButtonBox(
                txt = "Confirm",
                enabled = uiState.selectedId != null,
                bgColor = Primary,
                contentColor = TextPrimaryLight
            ) {
                viewModel.handle(AddressIntent.Confirm)
            }
        }
    }
}

@Composable
private fun AddressTopBar(onBack: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .clickable { onBack() }
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Address",
            style = MaterialTheme.typography.titleMedium,
            color = Black
        )
    }
}

@Composable
private fun LocationSearchBox(
    onClick: () -> Unit = { }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(1.dp, TextSecondary.copy(alpha = 0.2f), RoundedCornerShape(18.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp)
            .clickable {
                onClick()
            }
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(R.drawable.ic_location),
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "San Diego, CA",
            style = MaterialTheme.typography.bodyMedium,
            color = Black,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(InputBackground),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_address),
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun AddressRow(
    address: DtoAddress,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val borderColor = if (isSelected) Primary else TextSecondary.copy(alpha = 0.25f)
    val backgroundColor = if (isSelected) Primary.copy(alpha = 0.05f) else Color.White

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(18.dp))
            .clickable { onSelect() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = address.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatAddressLine(address),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

private fun formatAddressLine(address: DtoAddress): String {
    val parts = listOf(address.detail, address.ward, address.district, address.city)
        .filter { it.isNotBlank() }
    return parts.joinToString(", ")
}

private fun DtoAddress.toSelectedAddress(): SelectedAddress {
    return SelectedAddress(
        name = name,
        detail = detail,
        ward = ward,
        district = district,
        city = city
    )
}