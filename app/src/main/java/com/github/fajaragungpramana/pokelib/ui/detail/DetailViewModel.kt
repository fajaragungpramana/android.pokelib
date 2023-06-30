package com.github.fajaragungpramana.pokelib.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.PokemonUseCase
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.domain.model.PokemonSpecies
import com.github.fajaragungpramana.pokelib.core.extension.onCompleteListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val mPokemonUseCase: PokemonUseCase) :
    ViewModel(), DetailEvent {

    private val _stateLoadingPokemonSpecies = MutableStateFlow(false)
    val stateLoadingPokemonSpecies: StateFlow<Boolean>
        get() = _stateLoadingPokemonSpecies.asStateFlow()

    private val _stateSuccessPokemonSpecies = MutableStateFlow(PokemonSpecies())
    val stateSuccessPokemonSpecies: StateFlow<PokemonSpecies>
        get() = _stateSuccessPokemonSpecies.asStateFlow()

    override fun getPokemonSpecies(id: Long): Job = viewModelScope.launch {
        mPokemonUseCase.getPokemonSpecies(id).collectLatest { result ->
            result.onCompleteListener(
                onLoading = {
                    _stateLoadingPokemonSpecies.value = it
                },
                onSuccess = {
                    _stateSuccessPokemonSpecies.value = it ?: PokemonSpecies()
                },
                onFailure = {

                },
                onError = {

                }
            )
        }
    }

}