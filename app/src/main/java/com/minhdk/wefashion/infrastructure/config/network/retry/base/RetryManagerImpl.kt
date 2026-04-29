package com.minhdk.wefashion.infrastructure.config.network.retry.base

import java.util.concurrent.ConcurrentHashMap

abstract class RetryManagerImpl: RetryManager {

    open val delayRetry = 1500L

    open val delaySpam = 3000L

    open val retryCount = 3

    open val isSuspend = false

    open val codes = emptyList<Int>()

    private val retryMap = ConcurrentHashMap<String, Int>()

    private val spamMap = ConcurrentHashMap<String, Long>()

    abstract fun delay()

    abstract suspend fun delaySuspend()

    override fun checkSpam(url: String): Boolean {
        val current = System.currentTimeMillis()
        return spamMap[url]?.let {
            if(current - it >= delaySpam) {
                spamMap.remove(url)
                false
            } else true
        } ?: false
    }

    override fun canRetry(url: String, code: Int?, delay: Boolean): Boolean {
        if(codes.isNotEmpty() && !codes.contains(code)) return false
        return retryMap[url]?.let {
            if(it < retryCount) {
                retryMap[url] = it + 1
                if(delay) delay()
                true
            } else {
                spamMap[url] = System.currentTimeMillis()
                retryMap.remove(url)
                false
            }
        } ?: run {
            retryMap[url] = 0
            if(delay) delay()
            false
        }
    }

    override suspend fun canRetrySuspend(url: String, code: Int?, delay: Boolean): Boolean {
        return retryMap[url]?.let {
            if(it < retryCount) {
                retryMap[url] = it + 1
                if(delay) delaySuspend()
                true
            } else {
                spamMap[url] = System.currentTimeMillis()
                retryMap.remove(url)
                false
            }
        } ?: run {
            retryMap[url] = 0
            if(delay) delaySuspend()
            false
        }
    }

    override fun onRequestSuccess(url: String) {
        retryMap.remove(url)
        spamMap.remove(url)
    }

}