package com.minhdk.wefashion.infrastructure.config.network.token.base

interface TokenFetcher<T, B> {
    fun fetch(body: B?): T?
}