package com.minhdk.wefashion.infrastructure.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteAddress(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("ward")
    @Expose
    val ward: String,
    @SerializedName("district")
    @Expose
    val district: String,
    @SerializedName("city")
    @Expose
    val city: String,
    @SerializedName("detail")
    @Expose
    val detail: String,
    @SerializedName("latitude")
    @Expose
    val latitude: Double,
    @SerializedName("longitude")
    @Expose
    val longitude: Double,
    @SerializedName("receiver_name")
    @Expose
    val receiverName: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("is_default")
    @Expose
    val isDefault: Boolean,
    @SerializedName("user_id")
    @Expose
    val userId: Int
)

