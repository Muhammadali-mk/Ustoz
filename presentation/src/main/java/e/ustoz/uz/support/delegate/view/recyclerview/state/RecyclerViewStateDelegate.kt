package e.ustoz.uz.support.delegate.view.recyclerview.state

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.support.delegate.view.recyclerview.RecyclerViewDelegate
import e.ustoz.uz.view.recyclerview.state.StateViews
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateView
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateViewAdapter
import kotlin.properties.Delegates
import kotlin.reflect.KClass

abstract class RecyclerViewStateDelegate<T : Any>(
    target: Any?,
    lifecycleOwner: LifecycleOwner?
) : RecyclerViewDelegate<T>(target, lifecycleOwner) {

    constructor(lifecycleOwner: LifecycleOwner?) : this(null, lifecycleOwner)

    private lateinit var stateAdapter: RecyclerViewStateViewAdapter

    private var loadingStateViewKClass: KClass<out RecyclerViewStateView> by Delegates.notNull()
    private var emptyStateViewKClass: KClass<out RecyclerViewStateView> by Delegates.notNull()
    private var errorStateView: StateViews.ErrorStateView by Delegates.notNull()

    @CallSuper
    override fun onCreate(view: RecyclerView, savedInstanceState: Bundle?) {
        super.onCreate(view, savedInstanceState)
        stateAdapter = RecyclerViewStateViewAdapter(recyclerViewAdapter).also { view.adapter = it }
            .apply {
                registerStateView(getLoadingStateView().also { loadingStateViewKClass = it::class })
                registerStateView(getEmptyStateView().also { emptyStateViewKClass = it::class })
                registerStateView(
                    getErrorStateView(::handleErrorStateClick).also { errorStateView = it }
                )
            }
    }

    protected open fun getLoadingStateView(): RecyclerViewStateView =
        StateViews.LoadingStateView()

    protected open fun getEmptyStateView(): RecyclerViewStateView =
        StateViews.EmptyStateView()

    protected open fun getErrorStateView(clickListener: () -> Unit): StateViews.ErrorStateView =
        StateViews.ErrorStateView(clickListener)

    protected open fun handleErrorStateClick() {
    }

    protected fun registerStateView(stateView: RecyclerViewStateView) =
        stateAdapter.registerStateView(stateView)

    protected fun setStateView(kClass: KClass<out RecyclerViewStateView>) =
        stateAdapter.setStateView(kClass)

    open fun onLoading() =
        setStateView(loadingStateViewKClass)

    open fun onSuccess(elements: Collection<T>, withUpsert: Boolean = false) {
        if (recyclerViewAdapter.isEmpty && elements.isEmpty()) setStateView(emptyStateViewKClass)
        else {
            setStateView(RecyclerViewStateViewAdapter.ContentStateView::class)
            if (withUpsert) upsertAll(elements) else addAll(elements)
        }
    }

    fun onFailure(throwable: Throwable) {
        errorStateView.setThrowable(throwable)
        stateAdapter.setStateView(errorStateView::class)
    }
}