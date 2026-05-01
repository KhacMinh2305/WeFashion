package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): RequestResult<List<EntityCategory>>

    suspend fun getCategoryById(id: Int): RequestResult<EntityCategory>

    suspend fun getCategoriesWithFlow(): RequestResult<Flow<List<EntityCategory>>>

}