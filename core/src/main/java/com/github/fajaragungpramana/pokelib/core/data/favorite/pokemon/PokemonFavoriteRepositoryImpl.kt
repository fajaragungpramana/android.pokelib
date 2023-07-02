package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity
import com.github.fajaragungpramana.pokelib.core.extension.connection
import com.github.fajaragungpramana.pokelib.core.extension.resultFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonFavoriteRepositoryImpl @Inject constructor(
    private val mPokemonFavoriteDao: PokemonFavoriteDao
) : PokemonFavoriteRepository {

    override suspend fun setPokemonFavorite(request: PokemonFavoriteRequest): Flow<AppResult<PokemonFavoriteEntity>> =
        flow {
            emit(connection {
                mPokemonFavoriteDao.setPokemonFavorite(request.mapToObjectEntity())
                val pokemonFavorite = mPokemonFavoriteDao.getPokemonFavorite(
                    globalId = request.globalId
                ) ?: return@connection AppResult.OnFailure(code = 1)

                return@connection AppResult.OnSuccess(data = pokemonFavorite)
            })
        }.resultFlow()

    override suspend fun getPokemonFavorite(globalId: Long?): Flow<AppResult<PokemonFavoriteEntity>> =
        flow {
            emit(connection {
                val pokemonFavorite = mPokemonFavoriteDao.getPokemonFavorite(
                    globalId = globalId
                ) ?: return@connection AppResult.OnFailure(code = 1)

                return@connection AppResult.OnSuccess(data = pokemonFavorite)
            })
        }

}