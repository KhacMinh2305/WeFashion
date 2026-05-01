package com.minhdk.wefashion.infrastructure.remote.model.api.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteCategories(
    @SerializedName("categories")
    @Expose
    val categories: List<RemoteCategory>
)