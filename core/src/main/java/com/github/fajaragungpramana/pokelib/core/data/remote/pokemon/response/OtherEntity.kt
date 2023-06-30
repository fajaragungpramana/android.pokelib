package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class OtherEntity(

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkEntity? = null

)