package com.minhdk.wefashion.infrastructure.remote.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteAddressList(
    @SerializedName("user")
    @Expose
    val user: com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddressUser,
    @SerializedName("addresses")
    @Expose
    val addresses: List<com.minhdk.wefashion.infrastructure.remote.model.api.account.response.RemoteAddress>
)

