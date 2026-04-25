package com.minhdk.wefashion.infrastructure.config.network.base

import android.util.Log
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

abstract class BaseAuthenticator<T>(
    private val manager: TokenManager<T>
): Authenticator {

    private var retry = 0

    abstract fun provideRawToken(): String?

    override fun authenticate(route: Route?, response: Response): Request? {
        if(retry < 2) {
            retry++
            val req = response.request.url.toString()
            Log.d("vewsmn", "request: $req come in")
            synchronized(this) {
                Log.d("vewsmn", "request: $req start fetching")
                if(manager.getOrFetchLocking() == null) return null
                Log.d("vewsmn", "request: $req get token success. Send $req again")
                return provideRawToken()?.let {
                    Log.d("vewsmn", "token: $it")
                    response.request.newBuilder()
                        .header("Authorization", "Bearer $it")
                        .build()
                }
            }
        }
        retry = 0
        return null
    }
}