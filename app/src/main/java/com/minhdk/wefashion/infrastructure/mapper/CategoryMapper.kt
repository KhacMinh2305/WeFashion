package com.minhdk.wefashion.infrastructure.mapper

import com.minhdk.wefashion.infrastructure.database.room.entity.EntityCategory
import com.minhdk.wefashion.infrastructure.remote.model.api.category.RemoteCategory

fun RemoteCategory.toEntityCategory(): EntityCategory {
    return EntityCategory(
        id = id,
        name = name
    )
}

fun List<RemoteCategory>.toEntityCategories(): List<EntityCategory> {
    return map { it.toEntityCategory() }
}

