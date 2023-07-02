package com.github.fajaragungpramana.pokelib.favorite

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.fajaragungpramana.pokelib.di.AppComponentProvider
import com.github.fajaragungpramana.pokelib.favorite.di.DaggerFavoriteComponent
import com.github.fajaragungpramana.pokelib.favorite.ui.favorite.FavoriteView
import com.github.fajaragungpramana.pokelib.favorite.ui.favorite.FavoriteViewModel
import com.github.fajaragungpramana.pokelib.ui.RouteView
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import javax.inject.Inject

class FavoriteActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel by viewModels<FavoriteViewModel>(factoryProducer = { viewModelFactory })

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        DaggerFavoriteComponent.builder()
            .appComponent((applicationContext as AppComponentProvider).provideAppComponent())
            .build()
            .inject(this)

    }

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