package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.search.DtoSearchResult
import com.minhdk.wefashion.domain.repository.SearchRepository
import com.minhdk.wefashion.infrastructure.datasource.search.RemoteSearchDataSource
import com.minhdk.wefashion.infrastructure.mapper.model.toDto
import com.minhdk.wefashion.util.helper.logD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSearchDataSource
): SearchRepository {

    override suspend fun search(query: String, limit: Int): RequestResult<DtoSearchResult> {
        try {
            val raw = remoteSource.search(query, limit) ?: return RequestResult.Error(Exception("Data is null"))
            return RequestResult.Success(raw.toDto())
        } catch (e: Exception) {
            return RequestResult.Error(e)
        }
    }

}