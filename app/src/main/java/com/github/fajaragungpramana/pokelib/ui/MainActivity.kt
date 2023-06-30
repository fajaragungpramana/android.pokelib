package com.github.fajaragungpramana.pokelib.ui

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
import com.github.fajaragungpramana.pokelib.ui.detail.DetailView
import com.github.fajaragungpramana.pokelib.ui.main.MainView
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                    NavHost(navController = navController, startDestination = RouteView.MAIN.name) {
                        composable(RouteView.MAIN.name) {
                            MainView.ContentView(navController = navController)
                        }
                        composable(RouteView.DETAIL.name) {
                            DetailView.ContentView(navController = navController)
                        }
                    }

                }

            }
        }

    }

}