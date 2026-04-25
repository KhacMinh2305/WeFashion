package com.minhdk.wefashion.infrastructure.config.network.base

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

abstract class BaseAuthenticator<T>(
    private val manager: TokenManager<T>
): Authenticator {

    abstract fun provideRawToken(): String?

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            if(manager.getOrFetchLocking() == null) return null
            return provideRawToken()?.let {
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it}")
                    .build()
            }
        }
    }
}