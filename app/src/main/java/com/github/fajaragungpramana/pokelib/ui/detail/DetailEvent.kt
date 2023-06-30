package com.github.fajaragungpramana.pokelib.ui.detail

import kotlinx.coroutines.Job

interface DetailEvent {
    fun getPokemon(id: Long): Job
}