@file:Suppress("EXPERIMENTAL_API_USAGE")
package e.ustoz.data.utils.flow

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*

suspend fun <T> Flow<Result<T>>.collect(
    onStart: () -> Unit = {}, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit = {}
) = onStart { runCatching(onStart).onFailure(onFailure) }
    .collect { it ->
        it.onSuccess { runCatching { onSuccess.invoke(it) }.onFailure(onFailure) }
        it.onFailure {
            if (it !is CancellationException) {
                Log.wtf("Flow error:", it)
                onFailure(it)
            }
        }
    }

@Suppress("RemoveExplicitTypeArguments")
fun <T> Flow<T>.flatMapResult(): Flow<Result<T>> =
    map { Result.success(it) }.catch { emit(Result.failure(it)) }