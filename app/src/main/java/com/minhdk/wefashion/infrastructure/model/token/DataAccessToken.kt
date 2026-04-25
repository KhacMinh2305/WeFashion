package com.minhdk.wefashion.infrastructure.model.token

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class DataAccessToken(
    @SerializedName("token")
    @Expose
    val token: String?,
    @SerializedName("created_at")
    @Expose
    val createdAt: Long?,
    @SerializedName("expired_in")  // seconds
    @Expose
    val expiredIn: Int?
) {

    fun getExpiredTime(): Long? {
        if(createdAt == null || expiredIn == null) return null
        return createdAt + expiredIn * 1000
    }

}