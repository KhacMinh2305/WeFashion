package com.minhdk.wefashion.infrastructure.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

@Dao
interface DaoUser {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: EntityUser): Long

    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: Int): EntityUser?

    @Query("SELECT * FROM User WHERE name = :username")
    suspend fun getUserByUsername(username: String): EntityUser?
}

