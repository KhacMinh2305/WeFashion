package com.minhdk.wefashion.infrastructure.datasource.order.remote

import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.infrastructure.remote.model.api.order.request.RequestCreateOrderBody
import com.minhdk.wefashion.infrastructure.remote.model.api.order.response.ResponseCreateOrder
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

}