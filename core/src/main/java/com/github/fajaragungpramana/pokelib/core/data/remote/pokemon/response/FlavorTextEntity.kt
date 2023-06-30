package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class FlavorTextEntity(

    @SerializedName("flavor_text")
    val flavorText: String? = null

)