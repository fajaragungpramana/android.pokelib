package com.github.fajaragungpramana.pokelib.core.domain.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.PokemonSpecies
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<Pokemon>>>
    suspend fun getPokemonSpecies(id: Long?): Flow<AppResult<PokemonSpecies>>
    suspend fun setFavoritePokemon(request: PokemonFavoriteRequest): Flow<AppResult<Pokemon>>
}