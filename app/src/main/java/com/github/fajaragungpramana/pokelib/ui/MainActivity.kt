package com.github.fajaragungpramana.pokelib.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.ui.detail.DetailView
import com.github.fajaragungpramana.pokelib.ui.main.MainView
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import com.google.gson.Gson
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
                    NavHost(
                        navController = navController,
                        startDestination = RouteView.Main.route
                    ) {
                        composable(RouteView.Main.route) {
                            MainView.ContentView(navController = navController)
                        }
                        composable(
                            RouteView.Detail().route,
                            arguments = listOf(navArgument("pokemon") {
                                type = NavType.StringType
                                nullable = true
                            })
                        ) {
                            val pokemonArgument = it.arguments?.getString("pokemon") ?: ""
                            val jsonPokemon =
                                pokemonArgument.substring(1, pokemonArgument.length - 1)
                            val pokemon = Gson().fromJson(
                                jsonPokemon,
                                Pokemon::class.java
                            )
                            DetailView.ContentView(navController = navController, pokemon)
                        }
                    }

                }

            }
        }

    }

}