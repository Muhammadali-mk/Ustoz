package e.ustoz.uz.view.recyclerview.state.wrapper

import android.content.Context
import android.database.Observable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.view.recyclerview.state.RecyclerViewState
import e.ustoz.uz.view.recyclerview.state.RecyclerViewStateAdapter
import java.lang.reflect.Field
import kotlin.reflect.KClass

class RecyclerViewStateViewAdapter(
    recyclerViewAdapter: RecyclerView.Adapter<*>
) : RecyclerViewStateAdapter<RecyclerView.ViewHolder>() {

    init {
        if (recyclerViewAdapter is RecyclerViewStateAdapter<*>)
            throw RuntimeException("Use a different adapter")
    }

    @Suppress("UNCHECKED_CAST")
    private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> =
        recyclerViewAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>

    @Volatile private var inflater: LayoutInflater? = null
    private var internalCall: Boolean = false

    override val currentState: RecyclerViewStateView
        get() = super.currentState as RecyclerViewStateView

    @Suppress("DEPRECATION")
    fun registerStateView(stateView: RecyclerViewStateView) {
        internalCall = true; registerState(stateView)
    }

    @Suppress("DEPRECATION")
    fun setStateView(kClass: KClass<out RecyclerViewStateView>) {
        internalCall = true
        val actualKClass: KClass<out RecyclerViewState> =
            if (kClass == ContentStateView::class) ContentState::class else kClass
        setState(actualKClass, notify = true)
    }

    @Deprecated("Call 'registerStateView' instead")
    override fun registerState(state: RecyclerViewState) {
        if (!internalCall) throw RuntimeException("Call 'registerStateView' instead")
        else super.registerState(state)
    }

    @Deprecated("Call 'setStateView' instead")
    override fun setState(kClass: KClass<out RecyclerViewState>) {
        if (!internalCall) throw RuntimeException("Call 'setStateView' instead")
        else super.setState(kClass)
    }

    @Deprecated("Call 'setStateView' instead")
    override fun setState(kClass: KClass<out RecyclerViewState>, notify: Boolean) {
        if (!internalCall) throw RuntimeException("Call 'setStateView' instead")
        else super.setState(kClass, notify)
    }

    override fun getItemId(position: Int): Long =
        if (isStatePosition(position)) super.getItemId(position)
        else adapter.getItemId(position)

    override fun getItemsViewType(position: Int): Int =
        adapter.getItemViewType(position)

    override fun onCreateItemsViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder = adapter.onCreateViewHolder(parent, viewType)

    override fun onCreateStateViewHolder(
        parent: ViewGroup,
        state: RecyclerViewState,
        viewType: Int
    ): RecyclerView.ViewHolder =
        StateViewHolder(currentState, getLayoutInflater(parent.context), parent)

    override fun onBindItemsViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) = adapter.onBindViewHolder(holder, position, payloads)

    override fun onBindStateViewHolder(
        holder: RecyclerView.ViewHolder,
        state: RecyclerViewState,
        position: Int,
        payloads: MutableList<Any>
    ) = (holder as StateViewHolder).onBind()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (!isDataObserverRegistered) adapter.registerAdapterDataObserver(dataObserver)
        adapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        if (isDataObserverRegistered) adapter.unregisterAdapterDataObserver(dataObserver)
        adapter.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        if (holder is StateViewHolder) super.onFailedToRecycleView(holder)
        else adapter.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        if (holder is StateViewHolder) super.onViewAttachedToWindow(holder)
        else adapter.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        if (holder is StateViewHolder) super.onViewDetachedFromWindow(holder)
        else adapter.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is StateViewHolder) super.onViewRecycled(holder)
        else adapter.onViewRecycled(holder)
    }

    override fun getItemsCount(): Int =
        adapter.itemCount

    private val isDataObserverRegistered: Boolean
        get() {
            var isRegistered = false
            try {
                val clazz: Class<RecyclerView.Adapter<*>> = RecyclerView.Adapter::class.java
                val declaredField: Field = clazz.getDeclaredField("mObservable")
                declaredField.isAccessible = true
                val observable: Observable<*> = declaredField.get(adapter) as Observable<*>

                val observersField: Field = Observable::class.java.getDeclaredField("mObservers")
                observersField.isAccessible = true
                val list: List<*> = observersField.get(observable) as List<*>
                isRegistered = list.contains(dataObserver)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return isRegistered
        }

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {

        @Suppress("DEPRECATION")
        override fun onChanged() {
            internalCall = true
            setState(ContentState::class, notify = false)
            notifyDataSetChanged()
        }

        @Suppress("DEPRECATION")
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            internalCall = true
            setState(ContentState::class, notify = false)
            notifyItemRangeChanged(positionStart, itemCount)
        }

        @Suppress("DEPRECATION")
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            internalCall = true
            setState(ContentState::class, notify = false)
            notifyItemRangeChanged(positionStart, itemCount, payload)
        }

        @Suppress("DEPRECATION")
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//            internalCall = true
            setState(ContentState::class, notify = false)
            notifyItemRangeInserted(positionStart, itemCount)
        }

        @Suppress("DEPRECATION")
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            internalCall = true
            setState(ContentState::class, notify = false)
            notifyItemRangeRemoved(positionStart, itemCount)
        }

        @Suppress("DEPRECATION")
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            internalCall = true
            setState(ContentState::class, notify = false)
            notifyItemRangeChanged(fromPosition, toPosition, itemCount)
        }
    }

    private fun getLayoutInflater(context: Context) =
        if (inflater != null) checkNotNull(inflater) else synchronized(this) {
            inflater = LayoutInflater.from(context); return@synchronized checkNotNull(inflater)
        }

    private class StateViewHolder(
        private val stateView: RecyclerViewStateView,
        inflater: LayoutInflater,
        root: ViewGroup
    ) : RecyclerView.ViewHolder(stateView.onCreateView(inflater, root)) {

        fun onBind() = stateView.onBind()
    }

    object ContentStateView : RecyclerViewStateView() {
        override fun configureTypeByPosition(position: ItemViewPosition) =
            throw UnsupportedOperationException()

        override fun onCreateView(inflater: LayoutInflater, root: ViewGroup) =
            throw UnsupportedOperationException()

        override fun onBind() =
            throw UnsupportedOperationException()
    }
}