package com.minhdk.wefashion.domain.repository

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken

interface AuthenticationRepository {

    suspend fun getToken(): RequestResult<DataAccessToken?>

}