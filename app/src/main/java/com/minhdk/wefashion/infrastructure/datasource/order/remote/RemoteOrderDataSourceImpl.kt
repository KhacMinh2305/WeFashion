package com.minhdk.wefashion.infrastructure.datasource.order.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseOrderList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteOrderDataSourceImpl @Inject constructor(
    private val apiService: DataApiService
): RemoteOrderDataSource {

    override suspend fun createOrder(
        id: Int,
        body: RequestCreateOrderBody
    ): ResponseCreateOrder? = withContext(Dispatchers.IO) {
        apiService.createOrder(id, body).data
    }

    override suspend fun getOrders(userId: Int): ResponseOrderList? = withContext(Dispatchers.IO) {
        apiService.getOrders(userId).data
    }

}