package com.minhdk.wefashion.domain.repository.authentication

import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken

interface AuthenticationRepository {

    suspend fun getToken(): RequestResult<DataAccessToken?>

}
