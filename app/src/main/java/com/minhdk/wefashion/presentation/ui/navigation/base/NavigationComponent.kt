package com.minhdk.wefashion.presentation.ui.navigation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    selectedIconColor: Color = Color.Black,
    selectedTextColor: Color = Color.Black,
    unSelectedIconColor: Color = selectedIconColor.copy(alpha = 0.7f),
    unselectedTextColor: Color = selectedTextColor.copy(alpha = 0.7f),
    itemContainerColors: List<Color> = listOf(Color.Transparent, Color.Transparent),
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = { NavBarIcon(item) },
        label = { Text(item.label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedIconColor,
            selectedTextColor = selectedTextColor,
            unselectedIconColor = unSelectedIconColor,
            unselectedTextColor = unselectedTextColor,
            indicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp, 5.dp)
            .background(
                color = if (isSelected) itemContainerColors.first() else itemContainerColors.last(),
                shape = RoundedCornerShape(15.dp)
            )
    )
}

@Composable
fun BaseBottomBar(
    modifier: Modifier = Modifier,
    items: List<NavBarItem<*>>,
    isSelected: (Int) -> Boolean,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            BottomBarItem(
                item = item,
                isSelected = isSelected(item.position)
            ) {
                onItemSelected(item.position)
            }
        }

    }
}