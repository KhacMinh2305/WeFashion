package com.minhdk.wefashion.infrastructure.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class EntityProduct(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "sold_amount")
    val soldAmount: Int,
    @ColumnInfo(name = "liked_amount")
    val likedAmount: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "shop_id")
    val shopId: Int?
)

