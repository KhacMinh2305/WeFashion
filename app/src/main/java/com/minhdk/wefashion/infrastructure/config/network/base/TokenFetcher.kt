package com.minhdk.wefashion.infrastructure.config.network.base

interface TokenFetcher<T, B> {
    fun fetch(body: B?): T?
}