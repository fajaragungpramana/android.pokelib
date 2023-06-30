package com.github.fajaragungpramana.pokelib.ui.main

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.github.fajaragungpramana.pokelib.R
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.ui.RouteView
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import com.github.fajaragungpramana.pokelib.widget.component.TextFieldRoundedWithStartIcon
import com.google.gson.Gson

object MainView {

    @Composable
    fun ContentView(navController: NavController?) {
        val viewModel: MainViewModel = hiltViewModel()
        LaunchedEffect(Unit) {
            viewModel.getListPokemon(PokemonRequest())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            HeaderView {

            }

            val loadingListPokemon = viewModel.stateLoadingListPokemon.collectAsState()
            if (loadingListPokemon.value) PokemonShimmerView()

            val listPokemon = viewModel.stateSuccessListPokemon.collectAsState()
            if (listPokemon.value.isNotEmpty())
                PokemonView(
                    listPokemon = listPokemon.value,
                    onSelectedItem = {
                        val pokemon = Gson().toJson(it)
                        navController?.navigate(RouteView.Detail(pokemon = pokemon).route)
                    }
                )

        }
    }

    @Composable
    inline fun HeaderView(
        onSearchValueChanged: (String) -> Unit
    ) {

        val searchFieldValue = remember { mutableStateOf(TextFieldValue()) }
        onSearchValueChanged(searchFieldValue.value.text)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextFieldRoundedWithStartIcon(
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_search,
                placeHolderText = stringResource(id = R.string.search_pokemon_here),
                value = searchFieldValue
            )

            IconButton(
                onClick = {}
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    model = R.drawable.ic_favorite,
                    contentDescription = null
                )
            }

        }

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

    @Composable
    fun PokemonShimmerView() {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            )
        )
        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            content = {

                items(8) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable { },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .background(brush)
                                .fillMaxSize()
                        )

                    }

                }

            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Main_Preview() {
    PokeLibTheme(dynamicColor = false) {

        Surface(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            MainView.ContentView(navController = null)

        }

    }
}