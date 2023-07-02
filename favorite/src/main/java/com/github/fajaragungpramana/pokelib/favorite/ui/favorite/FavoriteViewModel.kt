package com.github.fajaragungpramana.pokelib.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.PokemonUseCase
import com.github.fajaragungpramana.pokelib.core.domain.pokemon.model.Pokemon
import com.github.fajaragungpramana.pokelib.core.extension.onCompleteListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val mPokemonUseCase: PokemonUseCase) :
    ViewModel(), FavoriteEvent {

    private val _stateSuccessListPokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val stateSuccessListPokemon: StateFlow<List<Pokemon>>
        get() = _stateSuccessListPokemon.asStateFlow()

    override fun getListPokemonFavorite(): Job = viewModelScope.launch {
        mPokemonUseCase.getListFavoritePokemon().collectLatest { result ->
            result.onCompleteListener(
                onLoading = {
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