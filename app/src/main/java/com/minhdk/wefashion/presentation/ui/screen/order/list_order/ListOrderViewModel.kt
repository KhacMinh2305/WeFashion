package com.minhdk.wefashion.presentation.ui.screen.order.list_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.OrderRepository
import com.minhdk.wefashion.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOrderViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val orderRepo: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListOrderState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: ListOrderIntent) {
        when (intent) {
            is ListOrderIntent.LoadOrders -> loadOrders()
        }
    }

    private fun loadOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val userRes = userRepo.getCachedUser()) {
                is RequestResult.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Vui long dang nhap")
                    }
                }

                is RequestResult.Success -> {
                    val userId = userRes.data.id
                    when (val result = orderRepo.getOrders(userId)) {
                        is RequestResult.Success -> {
                            val filtered = result.data.filter { it.orderState != 0 }
                            _uiState.update {
                                it.copy(isLoading = false, orders = filtered)
                            }
                        }

                        is RequestResult.Error -> {
                            _uiState.update {
                                it.copy(isLoading = false, errorMessage = result.error.message)
                            }
                        }
                    }
                }
            }
        }
    }
}

