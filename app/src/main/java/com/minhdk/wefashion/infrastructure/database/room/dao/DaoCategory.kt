package com.minhdk.wefashion.infrastructure.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCategory {

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<EntityCategory>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): EntityCategory?

    @Query("SELECT * FROM category")
    fun getCategoriesWithFlow(): Flow<List<EntityCategory>>

    @Insert(onConflict = REPLACE)
    suspend fun insertCategories(categories: List<EntityCategory>): List<Long>

    @Insert(onConflict = REPLACE)
    suspend fun insertCategory(category: EntityCategory): Long

    @Update(onConflict = REPLACE)
    suspend fun updateCategory(category: EntityCategory)

    @Delete
    suspend fun deleteCategory(category: EntityCategory)

    @Query("DELETE FROM category")
    suspend fun deleteAllCategories()

}