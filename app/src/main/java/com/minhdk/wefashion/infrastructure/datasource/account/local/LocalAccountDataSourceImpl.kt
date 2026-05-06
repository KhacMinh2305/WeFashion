package com.minhdk.wefashion.infrastructure.datasource.account.local

import com.minhdk.wefashion.infrastructure.database.room.dao.DaoAccount
import com.minhdk.wefashion.infrastructure.database.room.dao.DaoUser
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityAccount
import com.minhdk.wefashion.infrastructure.database.room.entity.EntityUser
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class LocalAccountDataSourceImpl @Inject constructor(
    private val daoAccount: DaoAccount
) : LocalAccountDataSource {

    override suspend fun insertAccount(account: EntityAccount): Long = withContext(Dispatchers.IO) {
        daoAccount.insertAccount(account)
    }
}

