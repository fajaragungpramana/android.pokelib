package com.github.fajaragungpramana.pokelib.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val mPokemonUseCase: PokemonUseCase) :
    ViewModel(), DetailEvent {

    override fun getPokemon(id: Long): Job = viewModelScope.launch {

    }

}