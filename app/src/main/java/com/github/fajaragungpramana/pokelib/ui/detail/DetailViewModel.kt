package com.github.fajaragungpramana.pokelib.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.pokelib.core.data.favorite.pokemon.request.PokemonFavoriteRequest
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.PokemonUseCase
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.PokemonSpecies
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

    private val _stateSuccessPokemonSpecies = MutableStateFlow(PokemonSpecies())
    val stateSuccessPokemonSpecies: StateFlow<PokemonSpecies>
        get() = _stateSuccessPokemonSpecies.asStateFlow()

    override fun getPokemonSpecies(id: Long?): Job = viewModelScope.launch {
        mPokemonUseCase.getPokemonSpecies(id).collectLatest { result ->
            result.onCompleteListener(
                onLoading = {
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

    override fun setPokemonFavorite(request: PokemonFavoriteRequest): Job = viewModelScope.launch {
        mPokemonUseCase.setFavoritePokemon(request).collectLatest { result ->
            result.onCompleteListener(
                onLoading = {
                },
                onSuccess = {
                },
                onFailure = {
                },
                onError = {
                }
            )
        }
    }

}