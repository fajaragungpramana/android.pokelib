package com.github.fajaragungpramana.pokelib.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.github.fajaragungpramana.pokelib.R
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.StatFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Stat
import com.github.fajaragungpramana.pokelib.extension.asDigit
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme

object DetailView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(navController: NavController?, pokemon: Pokemon?) {
        val viewModel: DetailViewModel = hiltViewModel()
        LaunchedEffect(Unit) {
            viewModel.getPokemonSpecies(pokemon?.id)
        }

        val pokemonSpecies = viewModel.stateSuccessPokemonSpecies.collectAsState()
        val pokemonFavorite = viewModel.stateSuccessPokemon.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.getPokemonFavorite(pokemon?.id)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = pokemon?.name.orEmpty().replaceFirstChar(Char::titlecase),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController?.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = {

                            if (pokemonFavorite.value.name == null) {
                                val listStatRequest = arrayListOf<StatFavoriteRequest>()
                                pokemon?.listStat?.forEach {
                                    listStatRequest.add(
                                        StatFavoriteRequest(
                                            value = it.value,
                                            name = it.name
                                        )
                                    )
                                }
                                viewModel.setPokemonFavorite(
                                    PokemonFavoriteRequest(
                                        globalId = pokemon?.id,
                                        name = pokemon?.name,
                                        image = pokemon?.image,
                                        about = pokemon?.about,
                                        height = pokemon?.height,
                                        weight = pokemon?.weight,
                                        listStat = listStatRequest
                                    )
                                )
                            } else
                                viewModel.deletePokemonFavorite(pokemon?.id)

                        }) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp),
                                model = if (pokemonFavorite.value.name == null) R.drawable.ic_favorite else R.drawable.ic_favorited,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .height(150.dp),
                        model = pokemon?.image,
                        contentDescription = null
                    )

                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .width(14.dp)
                                    .height(14.dp),
                                model = R.drawable.ic_weight,
                                contentDescription = null
                            )

                            Text(
                                text = pokemon?.weight?.asDigit(suffix = "KG").orEmpty(),
                                fontSize = 12.sp
                            )

                        }

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .width(14.dp)
                                    .height(14.dp),
                                model = R.drawable.ic_ruler,
                                contentDescription = null
                            )

                            Text(
                                text = pokemon?.height?.asDigit(suffix = "M").orEmpty(),
                                fontSize = 12.sp
                            )

                        }

                    }

                    Text(
                        text = pokemonSpecies.value.about.orEmpty(),
                        textAlign = TextAlign.Center
                    )

                    StatView(listStat = pokemon?.listStat)

                }
            }
        )
    }

    @Composable
    fun StatView(listStat: List<Stat>?) {
        if (listStat.isNullOrEmpty()) return

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            content = {

                items(listStat.size) {
                    val stat = listStat[it]

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            progress = (stat.value ?: 0) / 100f
                        )

                        Text(
                            text = stat.name.orEmpty(),
                            textAlign = TextAlign.Center
                        )

                    }

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