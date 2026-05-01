package com.minhdk.wefashion.infrastructure.datasource.category.local

import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

@Singleton
class LocalCategoryDataSourceImpl @Inject constructor(
    private val daoCategory: DaoCategory
): LocalCategoryDataSource {

    override suspend fun getCategories(): List<EntityCategory> = withContext(Dispatchers.IO) {
        daoCategory.getCategories()
    }

    override suspend fun getCategoryById(id: Int): EntityCategory? = withContext(Dispatchers.IO) {
        daoCategory.getCategoryById(id)
    }

    override suspend fun getCategoriesWithFlow(): Flow<List<EntityCategory>> {
        return daoCategory.getCategoriesWithFlow()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun insertCategories(categories: List<EntityCategory>): List<Long> =
        withContext(Dispatchers.IO) {
            daoCategory.insertCategories(categories)
        }

    override suspend fun insertCategory(category: EntityCategory): Long = withContext(Dispatchers.IO) {
        daoCategory.insertCategory(category)
    }

    override suspend fun updateCategory(category: EntityCategory) = withContext(Dispatchers.IO) {
        daoCategory.updateCategory(category)
    }

    override suspend fun deleteCategory(category: EntityCategory) = withContext(Dispatchers.IO) {
        daoCategory.deleteCategory(category)
    }

    override suspend fun deleteAllCategories() = withContext(Dispatchers.IO) {
        daoCategory.deleteAllCategories()
    }

}