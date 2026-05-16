package com.minhdk.wefashion.infrastructure.config.network.interceptor

import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenManager
import com.minhdk.wefashion.infrastructure.remote.model.token.DataAccessToken
import com.minhdk.wefashion.util.helper.logD
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor(
    private val manager: TokenManager<DataAccessToken>
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newReq = chain.request().newBuilder()
            .header("Authorization", "Bearer ${manager.getToken()?.token ?: ""}")
            .build()
        return chain.proceed(newReq)
    }

}