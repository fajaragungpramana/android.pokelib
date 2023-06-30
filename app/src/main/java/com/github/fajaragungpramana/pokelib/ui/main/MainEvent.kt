package com.github.fajaragungpramana.pokelib.ui.main

import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
import kotlinx.coroutines.Job

interface MainEvent {
    fun getListPokemon(request: PokemonRequest): Job
}