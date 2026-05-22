package com.minhdk.wefashion.infrastructure.datasource.order.remote

import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseOrderList

interface RemoteOrderDataSource {

    suspend fun createOrder(id: Int, body: RequestCreateOrderBody): ResponseCreateOrder?

    suspend fun getOrders(userId: Int): ResponseOrderList?

}