package com.github.fajaragungpramana.pokelib.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.getListPokemon(PokemonRequest())

        setContent {
            PokeLibTheme(dynamicColor = false) {

                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        MainView.HeaderView {

                        }

                        val loadingListPokemon = mViewModel.stateLoadingListPokemon.collectAsState()
                        if (loadingListPokemon.value) MainView.PokemonShimmerView()

                        val listPokemon = mViewModel.stateSuccessListPokemon.collectAsState()
                        if (listPokemon.value.isNotEmpty())
                            MainView.PokemonView(listPokemon = listPokemon.value)

                    }

                }

            }
        }

    }

}