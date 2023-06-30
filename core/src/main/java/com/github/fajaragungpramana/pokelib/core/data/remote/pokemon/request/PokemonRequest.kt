package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request

import com.github.fajaragungpramana.pokelib.core.constant.Http
import com.github.fajaragungpramana.pokelib.core.extension.removeNulls

data class PokemonRequest(
    var limit: Int? = 25,
    var offset: Int? = 1
) : Map<String, Any> by mapOf(
    Http.Param.LIMIT to limit,
    Http.Param.OFFSET to offset
).removeNulls()