package com.minhdk.wefashion.infrastructure.datasource.user.local

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

interface LocalUserDataSource {

    suspend fun insertUser(user: EntityUser): Long

    suspend fun getUserById(id: Int): EntityUser?

    suspend fun getUserByUsername(username: String): EntityUser?

}

