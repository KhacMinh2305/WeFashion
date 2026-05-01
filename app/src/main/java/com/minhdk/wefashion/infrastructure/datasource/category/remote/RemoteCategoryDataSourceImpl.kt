package com.minhdk.wefashion.infrastructure.datasource.category.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.category.RemoteCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCategoryDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
): RemoteCategoryDataSource {

    override suspend fun getCategories() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCategories().data?.categories.orEmpty()
    }

    override suspend fun getCategoryById(id: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCategory(id).data
    }

}