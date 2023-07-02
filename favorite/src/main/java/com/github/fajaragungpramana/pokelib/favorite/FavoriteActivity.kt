package com.github.fajaragungpramana.pokelib.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.fajaragungpramana.pokelib.favorite.ui.favorite.FavoriteView
import com.github.fajaragungpramana.pokelib.ui.RouteView
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme

class FavoriteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokeLibTheme(dynamicColor = false) {

                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = RouteView.Favorite.route
                    ) {
                        composable(RouteView.Favorite.route) {
                            FavoriteView.ContentView()
                        }
                    }

                }

            }
        }

    }

}