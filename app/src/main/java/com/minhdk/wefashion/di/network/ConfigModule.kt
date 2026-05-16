package com.minhdk.wefashion.di.network

import com.minhdk.wefashion.infrastructure.config.network.retry.InterceptorRetryManagerImpl
import com.minhdk.wefashion.infrastructure.config.network.retry.base.RetryManager
import com.minhdk.wefashion.infrastructure.config.network.token.DataTokenFetcherImpl
import com.minhdk.wefashion.infrastructure.config.network.token.DataTokenManagerImpl
import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenFetcher
import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenManager
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.api.details.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DataToken

    @Provides
    @Singleton
    @DataToken
    fun provideDataTokenFetcher(
        authService: AuthenticationService
    ): TokenFetcher<DataAccessToken, Nothing> {
        return DataTokenFetcherImpl(authService)
    }

    @Provides
    @Singleton
    fun provideTokenManager(@DataToken fetcher: TokenFetcher<DataAccessToken, Nothing>): TokenManager<DataAccessToken> {
        return DataTokenManagerImpl(fetcher)
    }

    @Provides
    fun provideInterceptorRetryManager(): RetryManager {
        return InterceptorRetryManagerImpl()
    }
}