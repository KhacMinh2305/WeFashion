package com.minhdk.wefashion.infrastructure.model.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class RemoteCategories(
    @SerializedName("categories")
    @Expose
    val categories: List<RemoteCategory>
)