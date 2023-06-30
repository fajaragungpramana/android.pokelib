package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model

import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity

data class Pokemon(
    var id: Long? = null,
    var name: String? = null,
    var image: String? = null,
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

    }

}