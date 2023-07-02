package com.github.fajaragungpramana.pokelib.favorite.di

import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.di.PokemonFavoriteModule
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.di.PokemonModule
import com.github.fajaragungpramana.pokelib.core.di.CoreModule
import com.github.fajaragungpramana.pokelib.core.domain.di.DomainModule
import com.github.fajaragungpramana.pokelib.di.AppComponent
import com.github.fajaragungpramana.pokelib.favorite.FavoriteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppComponent::class],
    modules = [CoreModule::class, DomainModule::class, PokemonModule::class, PokemonFavoriteModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

}