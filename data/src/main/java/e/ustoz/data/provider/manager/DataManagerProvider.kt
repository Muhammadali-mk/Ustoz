package e.ustoz.data.provider.manager

import android.content.Context
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.manager.coroutine.CoroutineContextManagerImpl

class DataManagerProvider internal constructor(private val context: Context) {
    val coroutineContextManager: CoroutineContextManager by lazy {
        CoroutineContextManagerImpl()
    }
}