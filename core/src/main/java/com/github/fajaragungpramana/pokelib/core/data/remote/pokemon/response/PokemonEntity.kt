package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class PokemonEntity(

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("sprites")
    val sprites: SpriteEntity? = null

)