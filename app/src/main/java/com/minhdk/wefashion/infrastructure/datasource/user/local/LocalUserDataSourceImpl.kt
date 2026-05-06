package com.minhdk.wefashion.infrastructure.datasource.user.local

import com.minhdk.wefashion.infrastructure.database.room.dao.DaoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class LocalUserDataSourceImpl @Inject constructor(
    private val daoUser: DaoUser
) : LocalUserDataSource {

    override suspend fun insertUser(user: EntityUser): Long = withContext(Dispatchers.IO) {
        daoUser.insertUser(user)
    }

    override suspend fun getUserById(id: Int): EntityUser? = withContext(Dispatchers.IO) {
        daoUser.getUserById(id)
    }

    override suspend fun getUserByUsername(username: String): EntityUser? = withContext(Dispatchers.IO) {
        daoUser.getUserByUsername(username)
    }

}

