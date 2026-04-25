package com.minhdk.wefashion.infrastructure.config.network.base

import com.minhdk.wefashion.util.helper.logD
import java.util.concurrent.atomic.AtomicReference

abstract class BaseTokenManagerImpl<T, B>(
    private val fetcher: TokenFetcher<T, B>
) : TokenManager<T> {

    protected abstract val tag: String
    protected val token = AtomicReference<T?>(null)

    /*
    * Should keep this up-to-date each time calling.
    * Ex: Compare expired-time with the calling time.
    * */
    abstract fun isExpired(): Boolean

    abstract fun provideBody(): B?

    //------------------------------------------------------
    //                   Helper functions
    //------------------------------------------------------
    private fun fetchAndSet(): T? {
        val t = fetcher.fetch(provideBody())
        token.set(t)
        return t
    }

    protected open fun log(message: String) {
        logD(tag, message)
    }

    //------------------------------------------------------
    //                  Business functions
    //------------------------------------------------------

    override fun getToken(): T? {
        return token.get()
    }

    override fun forceFetching(): T? {
        return try {
            synchronized(this) {
                fetchAndSet()
            }
        } catch (e: Exception) {
            log(e.toString())
            token.set(null)
            null
        }
    }

    override fun getOrFetchLocking(): T? {
        return try {
            synchronized(this) {
                if(isExpired()) fetchAndSet() else getToken()
            }
        } catch (e: Exception) {
            log(e.toString())
            token.set(null)
            null
        }
    }
}