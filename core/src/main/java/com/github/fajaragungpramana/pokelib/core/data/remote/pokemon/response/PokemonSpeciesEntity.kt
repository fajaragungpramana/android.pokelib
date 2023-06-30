package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesEntity(

    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntity>? = null

)