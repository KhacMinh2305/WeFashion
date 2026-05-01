package com.minhdk.wefashion.infrastructure.remote.model.api.account.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseForgotPasswordValidation(
    @SerializedName("is_valid")
    @Expose
    val isValid: Boolean?,
    @SerializedName("Detail")
    @Expose
    val detail: String?
)
