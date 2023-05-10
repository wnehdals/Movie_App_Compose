package com.jdm.app.movieappcompose.ui.main

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jdm.app.movieappcompose.R
import com.jdm.app.movieappcompose.ui.favorite.Favorite
import com.jdm.app.movieappcompose.ui.search.SearchScreen
import com.jdm.app.movieappcompose.ui.theme.C_4a5561
import com.jdm.app.movieappcompose.ui.theme.C_acb5bd

/*
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val colors = MaterialTheme.colorScheme
    val systemUiController = rememberSystemUiController()

    var statusBarColor by remember { mutableStateOf<Color>(colors.onPrimary) }
    var navigationBarColor by remember { mutableStateOf<Color>(colors.onPrimary) }

    val animatedStatusBarColor by animateColorAsState(
        targetValue = (statusBarColor),
        animationSpec = tween()
    )
    val animatedNavigationBarColor by animateColorAsState(
        targetValue = navigationBarColor,
        animationSpec = tween()
    )
    NavHost(navController = navController, startDestination = NavScreen.Search.route) {
        composable(NavScreen.Search.route) {
            MainNav(
                viewModel = hiltViewModel()
            )
            LaunchedEffect(Unit) {
                statusBarColor = colors.onPrimary
                navigationBarColor = colors.onPrimary
            }

        }
    }
    LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
        systemUiController.setStatusBarColor(animatedStatusBarColor)
        systemUiController.setNavigationBarColor(animatedNavigationBarColor)
    }
}


 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val viewState by viewModel.viewState.collectAsState()
    val bottomBarState by rememberSaveable { mutableStateOf(true)}
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current as Activity

    val systemUiController = rememberSystemUiController()
    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            bottomBar = {
                MovieBottomNavigation(
                    currentDestination = currentDestination,
                    navigateToScreen = { navigationItem ->
                        navigateBottomNavigationScreen(
                            navController = navController,
                            navigationItem = navigationItem
                        )
                    })
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = NavScreen.SEARCH.route
            ) {
                composable(route = NavScreen.SEARCH.route) {
                    SearchScreen(viewModel = hiltViewModel())
                }
                composable(route = NavScreen.FAVORITE.route) {
                    Favorite()
                }
            }

        }
    }


}
@Composable
fun MovieBottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigationItem) -> Unit
) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.navigationBarsPadding(),
        elevation = 24.dp
    ) {
        BottomNavigationItem.values().forEach { tab ->
            currentDestination?.hierarchy?.any { it.route == tab.route }?.let {
                BottomNavigationItem(
                    modifier = Modifier.padding(8.dp),
                    icon = {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            imageVector = ImageVector.vectorResource(id = tab.icon),
                            contentDescription = null,
                            //tint = LocalContentColor.current
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = tab.title),
                            color = C_acb5bd,
                            fontSize = 12.sp
                        )
                    },
                    selected = it,
                    onClick = { navigateToScreen(tab) },
                    selectedContentColor = C_4a5561,
                    unselectedContentColor = C_acb5bd
                )
            }
        }
    }
}

fun navigateBottomNavigationScreen(
    navController: NavController,
    navigationItem: BottomNavigationItem
) {
    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
enum class BottomNavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    SEARCH(NavScreen.SEARCH.route, R.string.str_search, R.drawable.ic_search_16_acb5bd),
    FAVORITE(NavScreen.FAVORITE.route, R.string.str_favorite, R.drawable.ic_heart_stroke_16_acb5bd);

}
enum class NavScreen(val route: String) {
    SEARCH("Search"),
    FAVORITE("Favorite");
}