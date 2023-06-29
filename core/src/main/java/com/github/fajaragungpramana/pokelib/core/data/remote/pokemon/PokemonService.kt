package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon

import com.github.fajaragungpramana.pokelib.core.app.AppListResponse
import com.github.fajaragungpramana.pokelib.core.constant.Http
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface PokemonService {

    @GET(Http.Route.POKEMON)
    suspend fun getListPokemon(@QueryMap request: PokemonRequest): Response<AppListResponse<PokemonEntity>>

    @GET("${Http.Route.POKEMON}/{${Http.Param.ID}}")
    suspend fun getPokemon(@Path(Http.Param.ID) id: Long?): Response<PokemonEntity>

}