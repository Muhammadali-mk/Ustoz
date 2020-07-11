package e.ustoz.data.manager.coroutine

import kotlin.coroutines.CoroutineContext

interface CoroutineContextManager {
    val defaultContext: CoroutineContext

    val ioContext: CoroutineContext

    val mainContext: CoroutineContext

    val unconfinedContext: CoroutineContext
}