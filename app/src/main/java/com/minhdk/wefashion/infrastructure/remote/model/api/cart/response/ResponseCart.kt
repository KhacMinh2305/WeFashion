package com.minhdk.wefashion.infrastructure.remote.model.api.cart.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseCart(
    @SerializedName("user")
    @Expose
    val user: ResponseCartUser?,
    @SerializedName("products")
    @Expose
    val products: List<ResponseCartProduct>?
)

data class ResponseCartUser(
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
    val bio: String?
)

data class ResponseCartProduct(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("rating")
    @Expose
    val rating: Double?,
    @SerializedName("sold_amount")
    @Expose
    val soldAmount: Int?,
    @SerializedName("liked_amount")
    @Expose
    val likedAmount: Int?,
    @SerializedName("category")
    @Expose
    val category: ResponseCartCategory?,
    @SerializedName("skus")
    @Expose
    val skus: List<ResponseCartSku>?
)

data class ResponseCartCategory(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?
)

data class ResponseCartSku(
    @SerializedName("sku")
    @Expose
    val sku: Int?,
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("price")
    @Expose
    val price: Int?,
    @SerializedName("product_id")
    @Expose
    val productId: Int?,
    @SerializedName("size")
    @Expose
    val size: ResponseCartSize?,
    @SerializedName("color")
    @Expose
    val color: ResponseCartColor?
)

data class ResponseCartSize(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?
)

data class ResponseCartColor(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("rgb")
    @Expose
    val rgb: String?
)

