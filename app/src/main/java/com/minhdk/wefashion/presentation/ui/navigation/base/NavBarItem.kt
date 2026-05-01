package com.minhdk.wefashion.presentation.ui.navigation.base


interface NavBarItem<T>{
    val position: Int
    val icon: T
    val label: String
}
