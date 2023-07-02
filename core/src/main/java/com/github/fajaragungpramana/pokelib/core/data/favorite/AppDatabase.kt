package com.github.fajaragungpramana.pokelib.core.data.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteDao
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity

@Database(entities = [PokemonFavoriteEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonFavoriteDao(): PokemonFavoriteDao
}