package com.minhdk.wefashion.infrastructure.config.network.token

import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenFetcher
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.AuthenticationService
import javax.inject.Inject

class DataTokenFetcherImpl @Inject constructor(
    private val authService: AuthenticationService
): TokenFetcher<DataAccessToken, Nothing> {

    override fun fetch(body: Nothing?): DataAccessToken? {
        return authService.fetchToken().execute().body()?.data
    }

}