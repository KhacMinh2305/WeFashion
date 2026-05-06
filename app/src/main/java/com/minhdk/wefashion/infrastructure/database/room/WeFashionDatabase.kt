package com.minhdk.wefashion.infrastructure.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoAccount
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoCategory
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoProduct
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityProduct
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityShop
import com.minhdk.wefashion.infrastructure.database.room.entity.EntitySku
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

@Database(
    entities = [
        EntityCategory::class,
        EntityAccount::class,
        EntityUser::class,
        EntityProduct::class,
        EntityShop::class,
        EntitySku::class
    ],
    version = 3
)
abstract class WeFashionDatabase : RoomDatabase() {

    abstract fun daoCategory(): DaoCategory

    abstract fun daoAccount(): DaoAccount

    abstract fun daoUser(): DaoUser

    abstract fun daoProduct(): DaoProduct

}