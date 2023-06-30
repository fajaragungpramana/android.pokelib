package com.github.fajaragungpramana.pokelib.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme

object DetailView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(navController: NavController?, pokemon: Pokemon?) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Detail") },
                    navigationIcon = {
                        IconButton(onClick = { navController?.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                }
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Detail_Preview() {
    PokeLibTheme(dynamicColor = false) {

        Surface(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            DetailView.ContentView(navController = null, null)

        }

    }
}