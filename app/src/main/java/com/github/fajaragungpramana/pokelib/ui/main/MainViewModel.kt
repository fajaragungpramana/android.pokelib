package com.github.fajaragungpramana.pokelib.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.PokemonUseCase
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.request.PokemonRequest
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
class MainViewModel @Inject constructor(private val mPokemonUseCase: PokemonUseCase) : ViewModel(),
    MainEvent {

    private val _stateLoadingListPokemon = MutableStateFlow(false)
    val stateLoadingListPokemon: StateFlow<Boolean>
        get() = _stateLoadingListPokemon.asStateFlow()

    private val _stateSuccessListPokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val stateSuccessListPokemon: StateFlow<List<Pokemon>>
        get() = _stateSuccessListPokemon.asStateFlow()

    override fun getListPokemon(request: PokemonRequest): Job = viewModelScope.launch {
        mPokemonUseCase.getListPokemon(request).collectLatest { result ->
            result.onCompleteListener(
                onLoading = {
                    _stateLoadingListPokemon.value = it
                },
                onSuccess = {
                    _stateSuccessListPokemon.value = it ?: emptyList()
                },
                onFailure = {

                },
                onError = {

                }
            )
        }
    }

}