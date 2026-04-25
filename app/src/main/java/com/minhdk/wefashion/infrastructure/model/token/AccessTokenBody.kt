package com.minhdk.wefashion.infrastructure.model.token

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenBody(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("token")
    @Expose
    val token: String = ""
)
