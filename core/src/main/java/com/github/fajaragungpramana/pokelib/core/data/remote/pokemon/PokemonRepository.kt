package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon(request: PokemonRequest): AppResult<Flow<List<Pokemon>>>
}