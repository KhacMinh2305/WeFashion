package com.minhdk.wefashion.infrastructure.config.network.authenticator

import com.minhdk.wefashion.infrastructure.config.network.token.base.TokenManager
import com.minhdk.wefashion.util.helper.logD
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseAuthenticator<T>(
    private val manager: TokenManager<T>
): Authenticator {

    protected open val maxRetry = 3
    protected open val retryDelay: Long = 2000L
    protected open val tag = "BaseAuthenticator"
    private val retryCount = AtomicInteger(0)

    // can add a time for delaying each time retry  4-times failed so that this optimizes performance and UX

    abstract fun provideRawToken(): String?

    override fun authenticate(route: Route?, response: Response): Request? {
        val url = response.request.url.toString()
        synchronized(this) {
            logD(tag, "request $url take place !")
            while(retryCount.get() <= maxRetry) {
                val time = retryCount.incrementAndGet()
                logD(tag, "Fetching times: $time")
                if(manager.getOrFetchLocking() != null) break
                Thread.sleep(retryDelay)
            }
            retryCount.set(0)
            if(manager.getToken() == null) logD(
                tag,
                "Token is still null after retrying, giving up !"
            )
            return provideRawToken()?.let {
                response.request.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            }
        }
    }
}