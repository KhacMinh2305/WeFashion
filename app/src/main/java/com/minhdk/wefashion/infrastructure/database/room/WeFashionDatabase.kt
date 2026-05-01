package com.minhdk.wefashion.infrastructure.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory

@Database(entities = [EntityCategory::class], version = 1)
abstract class WeFashionDatabase : RoomDatabase() {

    abstract fun daoCategory(): DaoCategory

}