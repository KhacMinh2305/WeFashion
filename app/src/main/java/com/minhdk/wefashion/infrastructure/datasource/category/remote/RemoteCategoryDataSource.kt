package com.minhdk.wefashion.infrastructure.datasource.category.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.category.RemoteCategory

interface RemoteCategoryDataSource {

    suspend fun getCategories(): List<RemoteCategory>

    suspend fun getCategoryById(id: Int): RemoteCategory?

}