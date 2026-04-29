package com.minhdk.wefashion.infrastructure.datasource.authentication

import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken

interface RemoteAuthenticationDataSource {

    suspend fun getToken(): DataAccessToken?

}