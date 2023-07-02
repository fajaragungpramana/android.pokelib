package com.github.fajaragungpramana.pokelib.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.fajaragungpramana.pokelib.core.util.ViewModelFactory
import com.github.fajaragungpramana.pokelib.core.util.ViewModelKey
import com.github.fajaragungpramana.pokelib.favorite.ui.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
interface FavoriteViewModelModule {

    @Binds
    fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun favoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

}