package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model

import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonEntity

data class Pokemon(
    var name: String?,
    var image: String?
) {

    companion object {

        fun mapToList(data: List<PokemonEntity>?) : List<Pokemon> {
            val listPokemon = ArrayList<Pokemon>()
            data?.forEach {
                listPokemon.add(
                    Pokemon(
                        name = it.name,
                        image = it.sprites?.other?.officialArtwork?.frontDefault
                    )
                )
            }

            return listPokemon
        }

    }

}