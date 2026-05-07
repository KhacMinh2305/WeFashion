package com.minhdk.wefashion.presentation.ui.screen.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepo: SearchRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState = _uiState.asStateFlow()

    fun handle(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Typing -> {
                _uiState.value = _uiState.value.copy(query = intent.query, isLoading = true)
            }
            is SearchIntent.Search -> {
                _uiState.value = _uiState.value.copy(isLoading = intent.query != null)
                intent.query?.let {
                    search(it)
                }
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = searchRepo.search(query, 10)) {
                is RequestResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        products = result.data.products
                    )
                }
                is RequestResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

}