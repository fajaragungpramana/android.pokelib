package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonFavoriteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "globalId")
    val globalId: Long?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "about")
    val about: String?,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "weight")
    val weight: Int?,

    @ColumnInfo(name = "listStat")
    val listStat: List<StatFavoriteEntity>?
)
