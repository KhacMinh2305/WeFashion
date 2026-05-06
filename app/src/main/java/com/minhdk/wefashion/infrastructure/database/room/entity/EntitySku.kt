package com.minhdk.wefashion.infrastructure.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sku")
data class EntitySku(
    @PrimaryKey
    @ColumnInfo(name = "sku")
    val sku: Int,
    @ColumnInfo(name = "amount")
    val amount: Int,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "size_id")
    val sizeId: Int,
    @ColumnInfo(name = "size_name")
    val sizeName: String,
    @ColumnInfo(name = "color_id")
    val colorId: Int,
    @ColumnInfo(name = "color_rgb")
    val colorRgb: String
)

