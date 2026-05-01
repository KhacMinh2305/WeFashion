package com.minhdk.wefashion.infrastructure.datasource.authentication

import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.api.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteAuthenticationDataSourceImpl @Inject constructor(
    private val authService: AuthenticationService
): RemoteAuthenticationDataSource {

    override suspend fun getToken(): DataAccessToken? {
        return authService.fetchToken().execute().body()?.data
    }

}