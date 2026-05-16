package com.minhdk.wefashion.infrastructure.datasource.order.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder

interface RemoteOrderDataSource {

    suspend fun createOrder(id: Int, body: RequestCreateOrderBody): ResponseCreateOrder?

}