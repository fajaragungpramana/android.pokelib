package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepository
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val mPokemonRepository: PokemonRepository) :
    PokemonUseCase {

    override suspend fun getListPokemon(request: PokemonRequest): AppResult<Flow<List<Pokemon>>> =
        mPokemonRepository.getListPokemon(request)

}