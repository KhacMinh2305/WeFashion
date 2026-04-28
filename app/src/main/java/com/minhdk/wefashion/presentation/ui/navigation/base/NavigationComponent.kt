package com.minhdk.wefashion.presentation.ui.navigation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
private fun NavBarIcon(item: NavBarItem<*>) {
    when(item.iconType) {
        NavBarItemResource.RESOURCE_INT -> {
            Icon(
                painter = painterResource(item.icon as Int),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        NavBarItemResource.IMAGE_VECTOR -> {
            Icon(
                imageVector = item.icon as ImageVector,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        NavBarItemResource.IMAGE_BITMAP -> {
            Icon(
                bitmap = item.icon as ImageBitmap,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
private fun RowScope.BottomBarItem(
    item: NavBarItem<*>,
    isSelected: Boolean,
    config: NavBarItemConfig,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = { NavBarIcon(item) },
        label = { Text(item.label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = config.selectedIcon,
            selectedTextColor = config.selectedLabel,
            unselectedIconColor = config.unselectedIcon,
            unselectedTextColor = config.unselectedLabel,
            indicatorColor = config.indicator
        ),
        modifier = Modifier
            .padding(config.pInside)
            .background(
                color = if (isSelected) config.selectedContainer else config.unselectedContainer,
                shape = config.containerShape
            )
    )
}

@Composable
fun AppBottomBar(
    items: List<NavBarItem<*>>,
    containerConfig: NavbarConfig = defaultNavBarConfig(),
    itemConfig: NavBarItemConfig,
    isSelected: (Int) -> Boolean,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = containerConfig.bgColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(itemConfig.height + itemConfig.pInside)
            .padding(horizontal = containerConfig.pHorizontal, vertical = containerConfig.pVertical)
            .navigationBarsPadding()
    ) {
        items.forEach { item ->
            BottomBarItem(
                item = item,
                isSelected = isSelected(item.position),
                itemConfig
            ) {
                onItemSelected(item.position)
            }
        }
    }
}