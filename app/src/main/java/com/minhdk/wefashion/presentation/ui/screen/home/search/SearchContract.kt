package com.minhdk.wefashion.presentation.ui.screen.home.search

import com.minhdk.wefashion.domain.data.search.DtoSearchProduct

data class SearchState(
    var query: String? = null,
    var isLoading: Boolean = false,
    val products: List<DtoSearchProduct> = emptyList(),
)

sealed class SearchIntent {
    data class Typing(val query: String): SearchIntent()
    data class Search(val query: String?): SearchIntent()
}

sealed class SearchEffect {

}