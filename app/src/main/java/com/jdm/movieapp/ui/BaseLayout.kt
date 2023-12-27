package com.jdm.movieapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jdm.design.theme.HNavigationBarItem
import com.jdm.design.theme.JdmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseLayout(
    backgroundColor: Color = JdmTheme.colors.white,
    bottomNavItem: List<BottomNavItem>?,
) {
    val navController: NavHostController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = backgroundColor,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            if (!bottomNavItem.isNullOrEmpty()) {
                NavigationBar {
                    bottomNavItem.forEachIndexed { _, navigationItem ->
                        HNavigationBarItem(
                            selected = navigationItem.route == currentDestination?.route,
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                            label = {
                                Text(text = navigationItem.label, style = JdmTheme.typography.L_XS)
                            }
                        )
                    }
                }
            }
        },
    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Search.route,
            modifier = Modifier.padding(paddingValues = paddingValue)
        ) {
            composable(BottomNavItem.Search.route) {
                SettingsScreen()
            }
            composable(BottomNavItem.Favorite.route) {
                SettingsScreen2()
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = JdmTheme.colors.blue300
    ) {
    }
}
@Composable
fun SettingsScreen2() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = JdmTheme.colors.magenta200
    ) {
    }
}