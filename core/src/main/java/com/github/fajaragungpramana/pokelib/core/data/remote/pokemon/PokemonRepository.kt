package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonSpeciesEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<PokemonEntity>>>
    suspend fun getPokemonSpecies(id: Long?): Flow<AppResult<PokemonSpeciesEntity>>
}