package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity
import kotlinx.coroutines.flow.Flow

interface PokemonFavoriteRepository {
    suspend fun setPokemonFavorite(request: PokemonFavoriteRequest): Flow<AppResult<PokemonFavoriteEntity>>
    suspend fun getPokemonFavorite(globalId: Long?): Flow<AppResult<PokemonFavoriteEntity>>
}