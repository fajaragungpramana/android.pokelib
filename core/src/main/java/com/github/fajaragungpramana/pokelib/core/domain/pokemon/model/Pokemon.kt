package com.github.fajaragungpramana.pokelib.core.domain.pokemon.model

import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity

data class Pokemon(
    var id: Long? = null,
    var name: String? = null,
    var image: String? = null,
    var about: String? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var listStat: List<Stat>? = null
) {

    companion object {

        fun mapToList(data: List<PokemonEntity>?): List<Pokemon> {
            if (data.isNullOrEmpty()) return emptyList()

            val listPokemon = arrayListOf<Pokemon>()
            for (i in data.indices) {
                val pokemonEntity = data[i]

                val pokemon = Pokemon(
                    id = pokemonEntity.id,
                    name = pokemonEntity.name,
                    image = pokemonEntity.sprites?.other?.officialArtwork?.frontDefault,
                    height = pokemonEntity.height,
                    weight = pokemonEntity.weight
                )

                if (pokemonEntity.stats.isNullOrEmpty())
                    listPokemon.add(pokemon)
                else {
                    val listStat = arrayListOf<Stat>()
                    for (j in pokemonEntity.stats.indices) {
                        val statEntity = pokemonEntity.stats[j]
                        listStat.add(
                            Stat(
                                value = statEntity.baseStat,
                                name = statEntity.stat?.mapStat()
                            )
                        )
                    }
                    pokemon.listStat = listStat
                    listPokemon.add(pokemon)
                }
            }

            return listPokemon
        }

        fun mapFavoriteToList(data: List<PokemonFavoriteEntity>?): List<Pokemon> {
            if (data.isNullOrEmpty()) return emptyList()

            val listPokemon = arrayListOf<Pokemon>()
            for (i in data.indices) {
                val pokemonFavoriteEntity = data[i]

                val pokemon = Pokemon(
                    id = pokemonFavoriteEntity.globalId,
                    name = pokemonFavoriteEntity.name,
                    image = pokemonFavoriteEntity.image,
                    height = pokemonFavoriteEntity.height,
                    weight = pokemonFavoriteEntity.weight
                )

                if (pokemonFavoriteEntity.listStat.isNullOrEmpty())
                    listPokemon.add(pokemon)
                else {
                    val listStat = arrayListOf<Stat>()
                    for (j in pokemonFavoriteEntity.listStat.indices) {
                        val statFavoriteEntity = pokemonFavoriteEntity.listStat[j]
                        listStat.add(
                            Stat(
                                value = statFavoriteEntity.value,
                                name = statFavoriteEntity.name
                            )
                        )
                    }
                    pokemon.listStat = listStat
                    listPokemon.add(pokemon)
                }
            }

            return listPokemon
        }

        fun mapToObject(data: PokemonFavoriteEntity?): Pokemon {
            if (data == null) return Pokemon()

            val pokemon = Pokemon(
                id = data.globalId,
                name = data.name,
                image = data.image,
                about = data.about,
                height = data.height,
                weight = data.weight
            )

            if (data.listStat.isNullOrEmpty()) return pokemon

            val listStat = arrayListOf<Stat>()
            data.listStat.forEach {
                listStat.add(
                    Stat(
                        value = it.value,
                        name = it.name
                    )
                )
            }
            pokemon.listStat = listStat

            return pokemon
        }

    }

}