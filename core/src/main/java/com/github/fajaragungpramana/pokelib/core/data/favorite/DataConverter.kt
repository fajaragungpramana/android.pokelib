package com.github.fajaragungpramana.pokelib.core.data.favorite

import androidx.room.TypeConverter
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.StatFavoriteEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromListStatFavorite(value: List<StatFavoriteEntity>): String =
        Gson().toJson(value)

    @TypeConverter
    fun toListStatFavorite(value: String): List<StatFavoriteEntity> {
        val type = object : TypeToken<List<StatFavoriteEntity>>() {}.type
        return Gson().fromJson(value, type)
    }

}