package com.minhdk.wefashion.di

import com.minhdk.wefashion.infrastructure.config.network.DataTokenManagerImpl
import com.minhdk.wefashion.infrastructure.config.network.base.TokenFetcher
import com.minhdk.wefashion.infrastructure.config.network.base.TokenManager
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DataToken

    @Provides
    @Singleton
    @DataToken
    fun provideDataTokenFetcher(
        authService: AuthenticationService
    ): TokenFetcher<DataAccessToken, Nothing> {
        return object: TokenFetcher<DataAccessToken, Nothing> {
            override fun fetch(body: Nothing?): DataAccessToken? {
                return authService.fetchToken().execute().body()?.data
            }
        }
    }
}