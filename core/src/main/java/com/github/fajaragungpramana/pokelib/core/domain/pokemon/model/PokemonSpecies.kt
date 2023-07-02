package com.github.fajaragungpramana.pokelib.core.domain.pokemon.model

import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response.PokemonSpeciesEntity

data class PokemonSpecies(
    var about: String? = null
) {

    companion object {

        fun mapObject(data: PokemonSpeciesEntity?): PokemonSpecies {
            val pokemonSpecies = PokemonSpecies()
            if (data == null || data.flavorTextEntries.isNullOrEmpty()) return pokemonSpecies

            pokemonSpecies.about = data.flavorTextEntries[0].flavorText
            return pokemonSpecies
        }

    }

}