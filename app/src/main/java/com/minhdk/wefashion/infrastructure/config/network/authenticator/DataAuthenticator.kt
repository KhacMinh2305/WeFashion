package com.minhdk.wefashion.infrastructure.config.network.authenticator

import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenManager
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataAuthenticator @Inject constructor(
    private val manager: TokenManager<DataAccessToken>
): BaseAuthenticator<DataAccessToken>(manager) {

    override val tag = "DataAuthenticator"

    override fun provideRawToken(): String? {
        return manager.getToken()?.token
    }

}