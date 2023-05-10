package com.jdm.app.movieappcompose.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jdm.app.movieappcompose.R
import com.jdm.app.movieappcompose.ui.favorite.Favorite
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNav(
    viewModel: MainViewModel
) {
    val selectedTab = getTabFromResource(viewModel.selectedTab.value)
    val tabs = MovieAppTap.values()
    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id =R.string.app_name)) })
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    //modifier = Modifier.navigationBarsPadding()
                ) {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = tab == selectedTab,
                            icon = { androidx.compose.material.Icon(painterResource(id =tab.icon), contentDescription = null) },
                            label = { Text(text = stringResource(id = tab.title), color = Color.White, fontSize = 12.sp)},
                            onClick = { viewModel.selectTab(tab.title) },
                            //colors = NavigationBarItemDefaults.colors()

                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Crossfade(selectedTab) {destination ->
                when (destination) {
                    MovieAppTap.SEARCH -> Search(modifier = modifier)
                    MovieAppTap.FAVORITE -> Favorite(modifier = modifier)
                }

            }
        }
    }

}
enum class BottomNavigationItem(
    val route: NavScreen,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    SEARCH(NavScreen.Search, R.string.str_search, R.drawable.ic_search_16_acb5bd),
    FAVORITE(NavScreen.Favorite, R.string.str_favorite, R.drawable.ic_heart_stroke_16_acb5bd);

}



 */