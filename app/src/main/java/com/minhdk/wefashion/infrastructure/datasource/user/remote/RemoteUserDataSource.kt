package com.minhdk.wefashion.infrastructure.datasource.user.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.user.request.RequestUpdateUser
import com.minhdk.wefashion.infrastructure.remote.model.api.user.response.ResponseUser

interface RemoteUserDataSource {

    suspend fun getUserById(id: Int): ResponseUser?

    suspend fun updateUser(id: Int, body: RequestUpdateUser): ResponseUser?

}

