package com.jdm.movieapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Search : BottomNavItem("search", Icons.Default.Search, "검색")
    object Favorite : BottomNavItem("favorite", Icons.Default.FavoriteBorder, "즐겨찾기")
}
