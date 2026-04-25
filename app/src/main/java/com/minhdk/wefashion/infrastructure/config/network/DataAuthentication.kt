package com.minhdk.wefashion.infrastructure.config.network

import com.minhdk.wefashion.infrastructure.config.network.base.BaseAuthenticator
import com.minhdk.wefashion.infrastructure.config.network.base.TokenManager
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken

class DataAuthentication(
    private val manager: TokenManager<DataAccessToken>
): BaseAuthenticator<DataAccessToken>(manager) {

    override fun provideRawToken(): String? {
        return manager.getToken()?.token
    }

}