package com.github.fajaragungpramana.pokelib.core.extension

import com.github.fajaragungpramana.pokelib.core.app.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

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

fun <T> Flow<AppResult<T>>.resultFlow(): Flow<AppResult<T>> =
    try {
        onStart { emit(AppResult.OnLoading(isLoading = true)) }
            .onCompletion { emit(AppResult.OnLoading(isLoading = false)) }
            .catch {
                emit(AppResult.OnLoading(isLoading = false))
                emit(AppResult.OnError(it))
            }
            .flowOn(Dispatchers.IO)
    } catch (e: Throwable) {
        flow { emit(AppResult.OnError(e)) }
    }

inline fun <T, M> Flow<AppResult<T>>.resultMapper(crossinline map: (T?) -> M): Flow<AppResult<M>> =
    channelFlow<AppResult<M>> {
        collectLatest {
            it.onCompleteListener(
                onLoading = { isLoading ->
                    send(AppResult.OnLoading(isLoading = isLoading))
                },
                onSuccess = { data ->
                    send(AppResult.OnSuccess(map(data)))
                },
                onFailure = { code ->
                    send(AppResult.OnFailure(code = code))
                },
                onError = { throwable ->
                    send(AppResult.OnError(throwable))
                }
            )
        }
    }.flowOn(Dispatchers.IO)

inline fun <T> connection(run: () -> AppResult<T>): AppResult<T> =
    try {
        run.invoke()
    } catch (e: Throwable) {
        AppResult.OnError(e)
    }

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}