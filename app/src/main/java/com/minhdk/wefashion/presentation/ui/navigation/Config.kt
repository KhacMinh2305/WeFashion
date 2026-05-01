package com.minhdk.wefashion.presentation.ui.navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.navigation.base.NavBarItem
import com.minhdk.wefashion.presentation.ui.navigation.base.singleColorNavBarItemConfig

private fun <T> createNavBarItem(pos: Int, ic: T, label: String): NavBarItem<T> {
    return object: NavBarItem<T> {
        override val position = pos
        override val icon = ic
        override val label= label
    }
}

val navigationItems = listOf(
    createNavBarItem(0, R.drawable.ic_home_inactive ,"Home"),
    createNavBarItem(1, R.drawable.ic_order_inactive ,"My Order"),
    createNavBarItem(2, R.drawable.ic_favorite_inactive ,"Contact"),
    createNavBarItem(3, R.drawable.ic_user_inactive ,"Setting")
)

fun appNavBarItemConfig() = singleColorNavBarItemConfig(
    selected = Color.Magenta,
    unselected = Color.LightGray,
    containerAlpha = 0f,
    bordered = 12.dp,
    height = 110.dp
)