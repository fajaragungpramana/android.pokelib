package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class StatEntity(

    @SerializedName("base_stat")
    val baseStat: Int? = null,

    @SerializedName("stat")
    val stat: NameEntity? = null

)