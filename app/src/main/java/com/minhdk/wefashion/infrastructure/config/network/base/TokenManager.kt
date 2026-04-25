package com.minhdk.wefashion.infrastructure.config.network.base

interface TokenManager<T> {

    /*
    * Normal getting
    */
    fun getToken(): T?

    /*
    * Refresh token suspend with one execution at a time with no condition
    */
    fun forceFetching(): T?

    /*
    * Same as #forceRefresh, but just refresh one time by the first execution,
    * others may get the cached token. This is design for authenticators or
    * interceptors
    */
    fun getOrFetchLocking(): T?

}