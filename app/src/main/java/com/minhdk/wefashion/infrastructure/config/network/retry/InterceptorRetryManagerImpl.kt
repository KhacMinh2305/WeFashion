package com.minhdk.wefashion.infrastructure.config.network.retry

import com.minhdk.wefashion.infrastructure.config.network.retry.base.RetryManagerImpl

class InterceptorRetryManagerImpl: RetryManagerImpl() {

    override val deniedCodes = listOf(401)

    override fun delay() {
        Thread.sleep(delayRetry)
    }

    override suspend fun delaySuspend() {}

}