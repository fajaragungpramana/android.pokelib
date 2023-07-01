package com.github.fajaragungpramana.pokelib.ui

sealed class RouteView(val route: String) {
    object Main : RouteView(route = "main")
    data class Detail(var pokemon: String? = null) : RouteView(route = "detail?pokemon={${pokemon ?: "pokemon"}}")
    object Favorite : RouteView(route = "com.github.fajaragungpramana.pokelib.favorite.FavoriteActivity")
}