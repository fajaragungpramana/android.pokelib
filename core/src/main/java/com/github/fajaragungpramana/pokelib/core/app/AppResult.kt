package com.github.fajaragungpramana.pokelib.core.app

sealed class AppResult<T> {

    data class OnLoading<T>(val isLoading: Boolean) : AppResult<T>()
    data class OnSuccess<T>(val data: T?) : AppResult<T>()
    data class OnFailure<T>(val code: Int? = null) : AppResult<T>()
    data class OnError<T>(val throwable: Throwable) : AppResult<T>()

}