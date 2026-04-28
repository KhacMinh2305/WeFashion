package com.minhdk.wefashion.presentation.ui.navigation

import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.navigation.base.NavBarItem
import com.minhdk.wefashion.presentation.ui.navigation.base.NavBarItemResource

val navigationItems = listOf(
    createNavBarItem(0, NavBarItemResource.RESOURCE_INT, R.drawable.ic_home_inactive ,"Home"),
    createNavBarItem(1, NavBarItemResource.RESOURCE_INT, R.drawable.ic_order_inactive ,"My Order"),
    createNavBarItem(2, NavBarItemResource.RESOURCE_INT, R.drawable.ic_favorite_inactive ,"Contact"),
    createNavBarItem(3, NavBarItemResource.RESOURCE_INT, R.drawable.ic_user_inactive ,"Setting")
)

private fun <T> createNavBarItem(pos: Int, type: NavBarItemResource, ic: T, label: String): NavBarItem<T> {
    return object: NavBarItem<T> {
        override val position = pos
        override val iconType = type
        override val icon = ic
        override val label= label
    }
}