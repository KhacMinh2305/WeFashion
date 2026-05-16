package com.minhdk.wefashion.infrastructure.repositoryimpl

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.data.user.DtoUser
import com.minhdk.wefashion.domain.repository.UserRepository
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import com.minhdk.wefashion.infrastructure.datasource.user.local.LocalUserDataSource
import com.minhdk.wefashion.infrastructure.datasource.user.remote.RemoteUserDataSource
import com.minhdk.wefashion.infrastructure.mapper.helper.user.buildUpdateUserRequest
import com.minhdk.wefashion.infrastructure.mapper.model.toDtoUser
import com.minhdk.wefashion.infrastructure.mapper.model.toEntityUser
import com.minhdk.wefashion.util.helper.logD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val localSource: LocalUserDataSource,
    private val remoteSource: RemoteUserDataSource
) : UserRepository {

    private var userCache: DtoUser? = null

    override suspend fun getUserById(id: Int): RequestResult<DtoUser> {
        return try {
            val localUser = localSource.getUserById(id)
            if (localUser != null) return RequestResult.Success(localUser.toDtoUser())

            val remoteUser = remoteSource.getUserById(id)
                ?: throw NoSuchElementException("User not found for id=$id")
            val entity = remoteUser.toEntityUser() ?: throw Exception("Important field is null")
            localSource.insertUser(entity)
            RequestResult.Success(entity.toDtoUser())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun updateUser(
        id: Int,
        name: String,
        avatarUrl: String,
        email: String,
        phoneNumber: String,
        bio: String
    ): RequestResult<DtoUser> {
        return try {
            val body = buildUpdateUserRequest(id, name, avatarUrl, email, phoneNumber, bio)
            val remoteUser = remoteSource.updateUser(id, body)
                ?: throw Exception("Response is null")
            val entity = remoteUser.toEntityUser() ?: throw Exception("Important field is null")
            localSource.insertUser(entity)
            RequestResult.Success(entity.toDtoUser())
        } catch (e: Exception) {
            RequestResult.Error(e)
        }
    }

    override suspend fun cacheUser(entity: EntityUser) {
        userCache = entity.toDtoUser()
        localSource.insertUser(entity)
    }

    override suspend fun getCachedUser(): RequestResult<DtoUser> {
        return userCache?.let {
            RequestResult.Success(it)
        } ?: RequestResult.Error(Exception("User not found"))
    }

}

