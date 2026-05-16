package com.minhdk.wefashion.infrastructure.config.network.token

import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenFetcher
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.api.details.AuthenticationService
import javax.inject.Inject

class DataTokenFetcherImpl @Inject constructor(
    private val authService: AuthenticationService
): TokenFetcher<com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken, Nothing> {

    override fun fetch(body: Nothing?): com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken? {
        return authService.fetchToken().execute().body()?.data
    }

}