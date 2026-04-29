package com.minhdk.wefashion.infrastructure.config.network.interceptor

import com.minhdk.wefashion.infrastructure.config.network.retry.base.RetryManager
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val retryManager: RetryManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        while (true) {
            try {
                val response = chain.proceed(request)
                if (response.isSuccessful) {
                    retryManager.onRequestSuccess(url)
                    return response
                }

                if (!shouldRetry(url, response.code)) {
                    return response
                }

                response.close()
            } catch (e: IOException) {
                if (!shouldRetry(url, null)) {
                    throw e
                }
            }
        }
    }

    private fun shouldRetry(url: String, code: Int?): Boolean {
        if (retryManager.checkSpam(url)) return false
        return retryManager.canRetry(url, code, true)
    }

}