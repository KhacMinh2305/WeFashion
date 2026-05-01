package com.minhdk.wefashion.presentation.ui.navigation.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

data class NavbarConfig(
    val pHorizontal: Dp = 0.dp,
    val pVertical: Dp = 0.dp,
    val bgColor: Color,
    val bgShape: Shape
)

data class NavBarItemConfig(
    val selectedIcon: Color,
    val unselectedIcon: Color,
    val selectedLabel: Color,
    val unselectedLabel: Color,
    val indicator: Color,
    val selectedContainer: Color,
    val unselectedContainer: Color,
    val containerShape: Shape,
    val iconSize: Dp = 28.dp,
    val labelSize: TextUnit = TextUnit.Unspecified,
    val height: Dp = 100.dp,
    val pInside: Dp = 5.dp
)

fun defaultNavBarConfig() = NavbarConfig(
    bgColor = Color.White,
    bgShape = RoundedCornerShape(0.dp)
)

fun singleColorNavBarItemConfig(
    selected: Color,
    unselected: Color,
    containerAlpha:
    Float,
    bordered: Dp,
    height: Dp = 100.dp,
    pInside: Dp = 5.dp
) = NavBarItemConfig(
    selectedIcon = selected,
    unselectedIcon = unselected,
    selectedLabel = selected,
    unselectedLabel = unselected,
    indicator = Color.Transparent,
    selectedContainer = selected.copy(alpha = containerAlpha),
    unselectedContainer = Color.Transparent,
    containerShape = RoundedCornerShape(bordered),
    height = height,
    pInside = pInside
)