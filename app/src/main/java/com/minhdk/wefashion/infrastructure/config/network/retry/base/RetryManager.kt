package com.minhdk.wefashion.infrastructure.config.network.retry.base

interface RetryManager {

    fun checkSpam(url: String): Boolean

    fun canRetry(url: String, code: Int?, delay: Boolean): Boolean

    suspend fun canRetrySuspend(url: String, code: Int?, delay: Boolean): Boolean

    fun onRequestSuccess(url: String)

}