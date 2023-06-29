package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon

import com.github.fajaragungpramana.pokelib.core.BuildConfig
import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.constant.Http
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity
import com.github.fajaragungpramana.pokelib.core.extension.connection
import com.github.fajaragungpramana.pokelib.core.extension.resultFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val mPokemonService: PokemonService) :
    PokemonRepository {

    override suspend fun getListPokemon(request: PokemonRequest): Flow<AppResult<List<PokemonEntity>>> = flow {
        emit(connection {
            val listPokemonResponse = mPokemonService.getListPokemon(request)
            if (listPokemonResponse.isSuccessful) {
                val listPokemon = arrayListOf<PokemonEntity>()
                listPokemonResponse.body()?.results?.forEach { pokemonEntity ->
                    val removeContent = pokemonEntity.url?.replace(
                        "${BuildConfig.BASE_URL}${Http.Route.POKEMON}/",
                        ""
                    )
                    val id = removeContent?.replace("/", "")?.toLong()
                    val pokemonResponse = mPokemonService.getPokemon(id)
                    if (pokemonResponse.isSuccessful)
                        listPokemon.add(pokemonResponse.body()?.copy(id = id) ?: PokemonEntity())
                }

                AppResult.OnSuccess(listPokemon as List<PokemonEntity>)
            } else {
                AppResult.OnFailure(code = listPokemonResponse.code())
            }
        })
    }.resultFlow()

}