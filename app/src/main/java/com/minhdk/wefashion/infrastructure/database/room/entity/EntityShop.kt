package com.minhdk.wefashion.infrastructure.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shop")
data class EntityShop(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "rate_amount")
    val rateAmount: Int,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "followers")
    val followers: Int
)

