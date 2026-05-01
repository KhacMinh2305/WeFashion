package com.minhdk.wefashion.infrastructure.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoAccount
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

@Database(entities = [EntityCategory::class, EntityAccount::class, EntityUser::class], version = 2)
abstract class WeFashionDatabase : RoomDatabase() {

    abstract fun daoCategory(): DaoCategory

    abstract fun daoAccount(): DaoAccount

    abstract fun daoUser(): DaoUser

}