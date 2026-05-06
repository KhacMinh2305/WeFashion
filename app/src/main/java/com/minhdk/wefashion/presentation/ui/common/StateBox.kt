package com.minhdk.wefashion.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> StateBox(
    modifier: Modifier = Modifier,
    state: UiState<T>,
    loading: @Composable () -> Unit,
    success: @Composable (data: T) -> Unit,
    error: @Composable (ex: Exception) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        when (state) {
            is UiState.Loading -> {
                loading()
            }
            is UiState.Success -> {
                success(state.data)
            }
            is UiState.Error -> {
                error(state.exception)
            }
        }
    }
}