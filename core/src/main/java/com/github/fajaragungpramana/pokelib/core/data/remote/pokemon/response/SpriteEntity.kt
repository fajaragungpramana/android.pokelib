package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class SpriteEntity(

    @SerializedName("other")
    val other: OtherEntity? = null

)