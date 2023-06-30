package com.github.fajaragungpramana.pokelib.core.app

import com.google.gson.annotations.SerializedName

data class AppListResponse<T>(

    @SerializedName("results")
    val results: List<T>? = null

)
