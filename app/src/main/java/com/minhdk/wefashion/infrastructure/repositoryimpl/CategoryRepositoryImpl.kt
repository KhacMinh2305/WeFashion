package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.CategoryRepository
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import com.minhdk.wefashion.infrastructure.datasource.category.local.LocalCategoryDataSource
import com.minhdk.wefashion.infrastructure.datasource.category.remote.RemoteCategoryDataSource
import com.minhdk.wefashion.infrastructure.mapper.toEntityCategories
import com.minhdk.wefashion.infrastructure.mapper.toEntityCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val localSource: LocalCategoryDataSource,
    private val remoteSource: RemoteCategoryDataSource
): CategoryRepository {

    override suspend fun getCategories(): RequestResult<List<EntityCategory>> {
        try {
            val localCategories = localSource.getCategories()
            if (localCategories.isNotEmpty()) return RequestResult.Success(localCategories)

            val remoteCategories = remoteSource.getCategories()
            if (remoteCategories.isEmpty()) return RequestResult.Success(emptyList())

            val entities = remoteCategories.toEntityCategories()
            localSource.insertCategories(entities)
            return RequestResult.Success(entities)
        } catch (error: Exception) {
            return RequestResult.Error(error)
        }
    }

    override suspend fun getCategoryById(id: Int): RequestResult<EntityCategory> {
        return try {
            localSource.getCategoryById(id)?.let {
                RequestResult.Success(it)
            } ?: run {
                val remoteCategory = remoteSource.getCategoryById(id)
                    ?: throw NoSuchElementException("Category not found for id=$id")
                val entity = remoteCategory.toEntityCategory()
                localSource.insertCategory(entity)
                return@run RequestResult.Success(entity)
            }
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

    override suspend fun getCategoriesWithFlow(): RequestResult<Flow<List<EntityCategory>>> {
        return try {
            val localCategories = localSource.getCategories()
            if (localCategories.isEmpty()) {
                val remoteCategories = remoteSource.getCategories()
                if (remoteCategories.isNotEmpty()) {
                    localSource.insertCategories(remoteCategories.toEntityCategories())
                }
            }
            RequestResult.Success(localSource.getCategoriesWithFlow())
        } catch (error: Exception) {
            RequestResult.Error(error)
        }
    }

}