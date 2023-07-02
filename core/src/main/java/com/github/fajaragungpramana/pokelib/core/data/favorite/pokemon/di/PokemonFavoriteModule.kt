package com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.di

import com.github.fajaragungpramana.pokelib.core.data.favorite.AppDatabase
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteDao
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteRepository
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PokemonFavoriteModule {

    @Provides
    fun providePokemonFavoriteDao(appDatabase: AppDatabase): PokemonFavoriteDao =
        appDatabase.pokemonFavoriteDao()


    @Provides
    fun providePokemonFavoriteRepository(
        pokemonFavoriteDao: PokemonFavoriteDao
    ): PokemonFavoriteRepository =
        PokemonFavoriteRepositoryImpl(pokemonFavoriteDao)

}