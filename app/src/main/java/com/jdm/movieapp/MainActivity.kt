package com.jdm.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jdm.movieapp.ui.BaseLayout
import com.jdm.movieapp.ui.BottomNavItem
import com.jdm.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomNavItem = listOf<BottomNavItem>(BottomNavItem.Search, BottomNavItem.Favorite)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BaseLayout(bottomNavItem = bottomNavItem)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
