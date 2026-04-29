package com.minhdk.wefashion.infrastructure.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteAddressList(
    @SerializedName("user")
    @Expose
    val user: RemoteAddressUser,
    @SerializedName("addresses")
    @Expose
    val addresses: List<RemoteAddress>
)

