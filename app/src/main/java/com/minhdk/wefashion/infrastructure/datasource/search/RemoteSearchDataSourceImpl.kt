package com.minhdk.wefashion.infrastructure.datasource.search

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.search.response.ResponseSearchData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchDataSourceImpl @Inject constructor(
    private val apiService: DataApiService,
): RemoteSearchDataSource {

    override suspend fun search(query: String, limit: Int): ResponseSearchData? {
        return apiService.search(query, limit).data
    }

}