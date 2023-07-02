package com.github.fajaragungpramana.pokelib.ui.detail

import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import kotlinx.coroutines.Job

interface DetailEvent {
    fun getPokemonSpecies(id: Long?): Job
    fun setPokemonFavorite(request: PokemonFavoriteRequest): Job
    fun getPokemonFavorite(globalId: Long?): Job
}