package com.github.fajaragungpramana.pokelib.core.domain.di

import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.PokemonFavoriteRepository
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.PokemonRepository
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.PokemonInteractor
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.PokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providePokemonInteractor(
        pokemonRepository: PokemonRepository,
        pokemonFavoriteRepository: PokemonFavoriteRepository
    ): PokemonUseCase = PokemonInteractor(pokemonRepository, pokemonFavoriteRepository)

}