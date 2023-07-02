package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request

import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.StatFavoriteEntity

data class PokemonFavoriteRequest(
    var globalId: Long? = null,
    var name: String? = null,
    var image: String? = null,
    var about: String? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var listStat: List<StatFavoriteRequest>? = null
) {

    fun mapToObjectEntity(): PokemonFavoriteEntity {
        val listStatEntity = arrayListOf<StatFavoriteEntity>()
        listStat?.forEach {
            listStatEntity.add(
                StatFavoriteEntity(
                    value = it.value,
                    name = it.name
                )
            )
        }

        return PokemonFavoriteEntity(
            id = 0,
            globalId = globalId,
            name = name,
            image = image,
            about = about,
            height = height,
            weight = weight,
            listStat = listStatEntity
        )
    }

}