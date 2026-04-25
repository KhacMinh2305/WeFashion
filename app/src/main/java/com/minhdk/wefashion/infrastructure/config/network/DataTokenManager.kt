package com.minhdk.wefashion.infrastructure.config.network

import com.minhdk.wefashion.di.ConfigModule.DataToken
import com.minhdk.wefashion.infrastructure.config.network.base.BaseTokenManagerImpl
import com.minhdk.wefashion.infrastructure.config.network.base.TokenFetcher
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataTokenManagerImpl @Inject constructor(
    @DataToken fetcher: TokenFetcher<DataAccessToken, Nothing>
): BaseTokenManagerImpl<DataAccessToken, Nothing>(fetcher) {

    override val tag = "DataTokenManagerImpl"

    override fun isExpired(): Boolean {
        return token.get()?.getExpiredTime() == System.currentTimeMillis()
    }

    override fun provideBody(): Nothing? {
        return null
    }
}