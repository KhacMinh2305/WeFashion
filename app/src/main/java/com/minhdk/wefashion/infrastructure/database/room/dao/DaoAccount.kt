package com.minhdk.wefashion.infrastructure.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount

@Dao
interface DaoAccount {

    @Query("SELECT * FROM Account WHERE id = :id")
    suspend fun getAccountById(id: Int): EntityAccount?

    @Insert(onConflict = REPLACE)
    suspend fun insertAccount(account: EntityAccount): Long
}

