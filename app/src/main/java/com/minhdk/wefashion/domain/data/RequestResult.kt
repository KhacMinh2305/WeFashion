package com.minhdk.wefashion.domain.data

sealed class RequestResult<out T> {
    data class Error(val error: Exception): RequestResult<Nothing>()
    data class Success<out T>(val data: T): RequestResult<T>()
}
