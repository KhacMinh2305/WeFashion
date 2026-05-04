package com.minhdk.wefashion.di.network

import com.minhdk.wefashion.BuildConfig
import com.minhdk.wefashion.infrastructure.config.network.authenticator.DataAuthenticator
import com.minhdk.wefashion.infrastructure.config.network.interceptor.AppInterceptor
import com.minhdk.wefashion.infrastructure.config.network.interceptor.NetworkInterceptor
import com.minhdk.wefashion.infrastructure.config.network.retry.base.RetryManager
import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenManager
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import com.minhdk.wefashion.infrastructure.remote.api.AuthenticationService
import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataService(
        tokenManager: TokenManager<DataAccessToken>,
        authenticator: DataAuthenticator,
        retryManager: RetryManager
    ): DataApiService {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .authenticator(authenticator)
//            .addNetworkInterceptor(NetworkInterceptor(retryManager))
            .addInterceptor(AppInterceptor(tokenManager))
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DataApiService::class.java)
    }
}