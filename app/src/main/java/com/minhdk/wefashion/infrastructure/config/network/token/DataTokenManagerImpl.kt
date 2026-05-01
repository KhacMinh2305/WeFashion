package com.minhdk.wefashion.infrastructure.config.network.token

import com.minhdk.wefashion.di.network.ConfigModule
import com.minhdk.wefashion.infrastructure.config.network.token.base.BaseTokenManagerImpl
import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenFetcher
import javax.inject.Inject

class DataTokenManagerImpl @Inject constructor(
    @ConfigModule.DataToken fetcher: TokenFetcher<com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken, Nothing>
): BaseTokenManagerImpl<com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken, Nothing>(fetcher) {

    override val tag = "DataTokenManagerImpl"

    override fun isExpired(): Boolean {
        return token.get()?.let {
            it.getExpiredTime() == System.currentTimeMillis()
        } ?: true
    }

    override fun provideBody(): Nothing? {
        return null
    }
}