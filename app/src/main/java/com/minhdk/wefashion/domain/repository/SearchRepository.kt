package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.search.DtoSearchProducts

interface SearchRepository {

    suspend fun searchProducts(query: String?, limit: Int?): RequestResult<DtoSearchProducts>

}

