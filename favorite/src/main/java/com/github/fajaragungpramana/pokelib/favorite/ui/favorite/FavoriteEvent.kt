package com.github.fajaragungpramana.pokelib.favorite.ui.favorite

import kotlinx.coroutines.Job

interface FavoriteEvent {
    fun getListPokemonFavorite(): Job
}