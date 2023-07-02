package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.di

import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepository
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepositoryImpl
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    fun provideService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    fun provideRepository(pokemonService: PokemonService): PokemonRepository =
        PokemonRepositoryImpl(pokemonService)

}