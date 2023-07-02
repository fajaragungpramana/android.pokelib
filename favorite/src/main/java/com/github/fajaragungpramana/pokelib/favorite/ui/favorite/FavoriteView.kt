package com.github.fajaragungpramana.pokelib.favorite.ui.favorite

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.github.fajaragungpramana.pokelib.R
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.widget.style.Black80

object FavoriteView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(factory: ViewModelProvider.Factory) {
        val viewModel: FavoriteViewModel = viewModel(factory = factory)
        LaunchedEffect(Unit) {
            viewModel.getListPokemonFavorite()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Black80,
                            text = stringResource(R.string.my_favorites),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        val activity = (LocalContext.current as Activity)
                        IconButton(onClick = {
                            activity.finish()
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {

                            AsyncImage(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp),
                                model = R.drawable.ic_sort,
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

                    val listPokemon = viewModel.stateSuccessListPokemon.collectAsState()
                    if (listPokemon.value.isNotEmpty())
                        PokemonView(
                            listPokemon = listPokemon.value,
                            onSelectedItem = {

                            }
                        )

                }
            }
        )

    }

    @Composable
    inline fun PokemonView(
        listPokemon: List<Pokemon>,
        crossinline onSelectedItem: (Pokemon) -> Unit
    ) {

        LazyVerticalGrid(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            content = {

                items(listPokemon.size) {
                    val pokemon = listPokemon[it]

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable { onSelectedItem(pokemon) },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                                model = pokemon.image,
                                loading = { CircularProgressIndicator() },
                                contentDescription = null
                            )

                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = pokemon.name.orEmpty().replaceFirstChar(Char::titlecase),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }

                    }

                }

            }
        )

    }

}