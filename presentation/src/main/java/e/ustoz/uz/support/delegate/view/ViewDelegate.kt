package e.ustoz.uz.support.delegate.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import e.ustoz.uz.support.lifecycle.LifecycleDelegate
import java.lang.ref.WeakReference

open class ViewDelegate<V : View>(
    target: Any?,
    lifecycleOwner: LifecycleOwner?
) : LifecycleDelegate(lifecycleOwner) {

    constructor(lifecycleOwner: LifecycleOwner?) : this(null, lifecycleOwner)

    private val targetWeakReference: WeakReference<Any?>? = target?.let { WeakReference(it) }
    private var viewWeakReference: WeakReference<V>? = null

    @CallSuper
    open fun onCreate(view: V, savedInstanceState: Bundle?) {
        viewWeakReference = WeakReference(view)
    }

    open fun onConfigurationChanged(newConfig: Configuration?) {
    }

    open fun onSaveInstanceState(outState: Bundle?) {
    }

    @CallSuper
    override fun onDestroy() {
        targetWeakReference?.clear()
        viewWeakReference?.clear()
        super.onDestroy()
    }

    val view: V?
        get() = viewWeakReference?.get()

    protected val context: Context
        get() = view?.context
            ?: throw IllegalStateException("Delegate $this not attached to a context.")

    @Suppress("UNCHECKED_CAST")
    protected fun <T> getTarget(): T? =
        targetWeakReference?.get() as T?
}