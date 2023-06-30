package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.PokemonSpecies
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<Pokemon>>>
    suspend fun getPokemonSpecies(id: Long?): Flow<AppResult<PokemonSpecies>>
}