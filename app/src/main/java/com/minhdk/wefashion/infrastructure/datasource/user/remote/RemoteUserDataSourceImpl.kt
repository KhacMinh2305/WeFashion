package com.minhdk.wefashion.infrastructure.datasource.user.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.user.request.RequestUpdateUser
import com.minhdk.wefashion.infrastructure.remote.model.api.user.response.ResponseUser
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class RemoteUserDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
) : RemoteUserDataSource {

    override suspend fun getUserById(id: Int): ResponseUser? = withContext(Dispatchers.IO) {
        apiService.getUserById(id).data
    }

    override suspend fun updateUser(
        id: Int,
        body: RequestUpdateUser
    ): ResponseUser? = withContext(Dispatchers.IO) {
        apiService.updateUser(id, body).data
    }

}

