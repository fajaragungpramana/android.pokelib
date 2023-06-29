package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon

import com.github.fajaragungpramana.pokelib.core.BuildConfig
import com.github.fajaragungpramana.pokelib.core.app.AppResult
import com.github.fajaragungpramana.pokelib.core.constant.Http
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.extension.connection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val mPokemonService: PokemonService) :
    PokemonRepository {

    override suspend fun getListPokemon(request: PokemonRequest): AppResult<Flow<List<Pokemon>>> =
        connection {
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
                    if (pokemonResponse.isSuccessful) {
                        listPokemon.add(pokemonResponse.body()?.copy(id = id) ?: PokemonEntity())
                    }
                }

                AppResult.OnSuccess(flow { emit(Pokemon.mapToList(listPokemon)) }.flowOn(Dispatchers.IO))
            } else {
                AppResult.OnFailure(code = listPokemonResponse.code())
            }
        }

}