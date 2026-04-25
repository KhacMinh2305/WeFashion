package com.minhdk.wefashion.infrastructure.model.baseResponse

class BaseResponse<T>(
    val statusCode: Int,
    val time: String,
    val data: T?
)