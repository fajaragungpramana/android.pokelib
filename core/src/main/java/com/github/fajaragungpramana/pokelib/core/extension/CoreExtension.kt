package com.github.fajaragungpramana.pokelib.core.extension

import com.github.fajaragungpramana.pokelib.core.app.AppResult

inline fun <T> AppResult<T>.onCompleteListener(
    onLoading: (Boolean) -> Unit,
    onSuccess: (T?) -> Unit,
    onFailure: (Int?) -> Unit,
    onError: (Throwable) -> Unit
) {
    when (this) {
        is AppResult.OnLoading -> onLoading(this.isLoading)
        is AppResult.OnSuccess -> onSuccess(this.data)
        is AppResult.OnFailure -> onFailure(this.code)
        is AppResult.OnError -> onError(this.throwable)
    }
}

inline fun <T> connection(run: () -> AppResult<T>): AppResult<T> {
    AppResult.OnLoading<T>(true)
    return try {
        run()
    } catch (e: Throwable) {
        AppResult.OnLoading<T>(false)
        AppResult.OnError(e)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}