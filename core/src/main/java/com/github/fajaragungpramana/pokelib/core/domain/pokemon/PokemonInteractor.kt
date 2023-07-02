package com.github.fajaragungpramana.pokelib.core.domain.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteRepository
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepository
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.PokemonSpecies
import com.github.fajaragungpramana.pokelib.core.extension.resultMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInteractor @Inject constructor(
    private val mPokemonRepository: PokemonRepository,
    private val mPokemonFavoriteRepository: PokemonFavoriteRepository
) : PokemonUseCase {

    override suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<Pokemon>>> =
        mPokemonRepository.getListPokemon(request).resultMapper { Pokemon.mapToList(it) }

    override suspend fun getPokemonSpecies(id: Long?): Flow<AppResult<PokemonSpecies>> =
        mPokemonRepository.getPokemonSpecies(id).resultMapper { PokemonSpecies.mapObject(it) }

    override suspend fun setFavoritePokemon(request: PokemonFavoriteRequest): Flow<AppResult<Pokemon>> =
        mPokemonFavoriteRepository.setPokemonFavorite(request)
            .resultMapper { Pokemon.mapToObject(it) }

    override suspend fun getFavoritePokemon(globalId: Long?): Flow<AppResult<Pokemon>> =
        mPokemonFavoriteRepository.getPokemonFavorite(globalId)
            .resultMapper { Pokemon.mapToObject(it) }

    override suspend fun deleteFavoritePokemon(globalId: Long?): Flow<AppResult<Pokemon>> =
        mPokemonFavoriteRepository.deletePokemonFavorite(globalId)
            .resultMapper { Pokemon.mapToObject(it) }

    override suspend fun getListFavoritePokemon(): Flow<AppResult<List<Pokemon>>> =
        mPokemonFavoriteRepository.getListPokemonFavorite()
            .resultMapper { Pokemon.mapFavoriteToList(it) }

}