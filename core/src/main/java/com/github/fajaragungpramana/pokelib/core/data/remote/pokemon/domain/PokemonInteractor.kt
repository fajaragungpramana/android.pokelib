package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepository
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.PokemonSpecies
import com.github.fajaragungpramana.pokelib.core.extension.resultMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val mPokemonRepository: PokemonRepository) :
    PokemonUseCase {

    override suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<Pokemon>>> =
        mPokemonRepository.getListPokemon(request).resultMapper { Pokemon.mapToList(it) }

    override suspend fun getPokemonSpecies(id: Long?): Flow<AppResult<PokemonSpecies>> =
        mPokemonRepository.getPokemonSpecies(id).resultMapper { PokemonSpecies.mapObject(it) }

}