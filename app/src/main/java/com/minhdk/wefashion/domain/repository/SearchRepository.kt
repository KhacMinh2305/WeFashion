package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.search.DtoSearchResult

interface SearchRepository {

    suspend fun search(query: String, limit: Int): RequestResult<DtoSearchResult>

}

