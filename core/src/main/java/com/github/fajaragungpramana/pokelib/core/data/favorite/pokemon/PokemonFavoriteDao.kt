package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.response.PokemonFavoriteEntity

@Dao
interface PokemonFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setPokemonFavorite(pokemonFavoriteEntity: PokemonFavoriteEntity)

    @Query("SELECT * FROM PokemonFavoriteEntity WHERE globalId = :globalId")
    fun getPokemonFavorite(globalId: Long?): PokemonFavoriteEntity?

    @Query("DELETE FROM PokemonFavoriteEntity WHERE globalId = :globalId")
    fun deletePokemonFavorite(globalId: Long?)

    @Query("SELECT * FROM PokemonFavoriteEntity")
    fun getListPokemonFavorite(): List<PokemonFavoriteEntity>?

}