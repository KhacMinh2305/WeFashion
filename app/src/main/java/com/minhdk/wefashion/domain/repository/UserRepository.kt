package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser

interface UserRepository {

    suspend fun cacheUser(entity: EntityUser)

    suspend fun getUserById(id: Int): RequestResult<DtoUser>

    suspend fun updateUser(
        id: Int,
        name: String,
        avatarUrl: String,
        email: String,
        phoneNumber: String,
        bio: String
    ): RequestResult<DtoUser>

    suspend fun getCachedUser(username: String): RequestResult<DtoUser>

}

