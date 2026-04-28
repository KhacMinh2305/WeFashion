package com.minhdk.wefashion.presentation.ui.navigation.base

enum class NavBarItemResource {
    IMAGE_VECTOR, IMAGE_BITMAP, RESOURCE_INT
}

interface NavBarItem<T>{
    val position: Int
    val iconType: NavBarItemResource
    val icon: T
    val label: String
}
