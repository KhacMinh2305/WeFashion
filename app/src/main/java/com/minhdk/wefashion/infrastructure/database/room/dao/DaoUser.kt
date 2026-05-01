package com.minhdk.wefashion.infrastructure.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

@Dao
interface DaoUser {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: EntityUser): Long
}

