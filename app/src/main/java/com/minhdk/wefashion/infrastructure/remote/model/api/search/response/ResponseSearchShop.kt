package com.minhdk.wefashion.infrastructure.remote.model.api.search.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseSearchShop(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String?,
    @SerializedName("email")
    @Expose
    val email: String?,
    @SerializedName("phone_number")
    @Expose
    val phoneNumber: String?,
    @SerializedName("bio")
    @Expose
    val bio: String?,
    @SerializedName("rate_amount")
    @Expose
    val rateAmount: Int?,
    @SerializedName("rating")
    @Expose
    val rating: Double?,
    @SerializedName("followers")
    @Expose
    val followers: Int?
)

