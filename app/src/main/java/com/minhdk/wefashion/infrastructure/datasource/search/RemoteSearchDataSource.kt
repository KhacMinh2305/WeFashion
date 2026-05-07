package com.minhdk.wefashion.infrastructure.datasource.search

import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchData

interface RemoteSearchDataSource {

    suspend fun search(query: String, limit: Int): ResponseSearchData?

}