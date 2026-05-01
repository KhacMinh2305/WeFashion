package com.minhdk.wefashion.infrastructure.datasource.category.local

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import kotlinx.coroutines.flow.Flow

interface LocalCategoryDataSource {

    suspend fun getCategories(): List<EntityCategory>

    suspend fun getCategoryById(id: Int): EntityCategory?

    suspend fun getCategoriesWithFlow(): Flow<List<EntityCategory>>

    suspend fun insertCategories(categories: List<EntityCategory>): List<Long>

    suspend fun insertCategory(category: EntityCategory): Long

    suspend fun updateCategory(category: EntityCategory)

    suspend fun deleteCategory(category: EntityCategory)

    suspend fun deleteAllCategories()

}