package com.minhdk.wefashion.infrastructure.datasource.account.local

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

interface LocalAccountDataSource {

    suspend fun insertAccount(account: EntityAccount): Long

}

