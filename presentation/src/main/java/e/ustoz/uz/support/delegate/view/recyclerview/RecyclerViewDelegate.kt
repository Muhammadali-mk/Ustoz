package e.ustoz.uz.support.delegate.view.recyclerview

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.support.delegate.view.ViewDelegate
import e.ustoz.uz.view.recyclerview.RecyclerViewAdapter

abstract class RecyclerViewDelegate<T : Any>(
    target: Any?,
    lifecycleOwner: LifecycleOwner?
) : ViewDelegate<RecyclerView>(target, lifecycleOwner) {

    constructor(lifecycleOwner: LifecycleOwner?) : this(null, lifecycleOwner)

    private var adapter: RecyclerViewAdapter<T, *>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    protected open val recyclerViewAdapter: RecyclerViewAdapter<T, *>
        get() = adapter ?: throw IllegalStateException("Adapter is not defined in delegate")

    protected open val recyclerViewLayoutManager: RecyclerView.LayoutManager
        get() = layoutManager ?: throw IllegalStateException("Manager is not defined in delegate")

    protected val isEmpty: Boolean
        get() = adapter?.isEmpty ?: false

    protected val isNotEmpty: Boolean
        get() = !isEmpty

    @CallSuper
    override fun onCreate(view: RecyclerView, savedInstanceState: Bundle?) {
        super.onCreate(view, savedInstanceState)
        view.setHasFixedSize(true)
        adapter = getAdapter().also { view.adapter = it }
        layoutManager = getLayoutManager().also { view.layoutManager = it }
        getItemDecoration()?.forEach { view.addItemDecoration(it) }
    }

    open fun add(element: T) {
        adapter?.add(element)
    }

    open fun add(position: Int, element: T) {
        adapter?.add(position, element)
    }

    open fun addAll(collection: Collection<T>) {
        adapter?.addAll(collection)
    }

    open fun clear() {
        adapter?.clear()
    }

    open fun remove(element: T) {
        adapter?.remove(element)
    }

    open fun set(collection: Collection<T>) {
        adapter?.set(collection)
    }

    open fun update(element: T) {
        adapter?.update(element)
    }

    open fun update(position: Int, element: T) {
        adapter?.update(position, element)
    }

    open fun upsertAll(collection: Collection<T>) {
        adapter?.upsertAll(collection)
    }

    fun getPosition(element: T): Int =
        adapter?.getPosition(element) ?: 0

    fun getElement(position: Int): T? =
        adapter?.getElement(position)

    fun containsInAdapter(element: T): Boolean =
        adapter?.containsInAdapter(element) ?: false

    protected open fun getItemDecoration(): Array<RecyclerView.ItemDecoration>? = null

    protected abstract fun getAdapter(): RecyclerViewAdapter<T, *>

    protected abstract fun getLayoutManager(): RecyclerView.LayoutManager
}