package com.minhdk.wefashion.infrastructure.remote.model.api.address.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteAddressList(
    @SerializedName("user")
    @Expose
    val user: RemoteAddressUser?,
    @SerializedName("addresses")
    @Expose
    val addresses: List<RemoteAddress?>
)

