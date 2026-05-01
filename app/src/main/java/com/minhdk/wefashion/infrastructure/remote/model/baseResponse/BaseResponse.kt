package com.minhdk.wefashion.infrastructure.remote.model.baseResponse

class BaseResponse<T>(
    val statusCode: Int,
    val time: String,
    val data: T?
)