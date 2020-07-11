package e.ustoz.uz.view.recyclerview.state

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.view.recyclerview.state.RecyclerViewState.ItemViewPosition
import e.ustoz.uz.view.recyclerview.state.RecyclerViewState.ViewStateType
import kotlin.reflect.KClass

abstract class RecyclerViewStateAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private val statesMap: MutableMap<RecyclerViewState, Boolean> = hashMapOf()

    open fun registerState(state: RecyclerViewState) {
        if (statesMap.isEmpty()) statesMap[ContentState] = true
        statesMap[state] = false
    }

    open fun setState(kClass: KClass<out RecyclerViewState>) =
        setState(kClass, notify = true)

    protected open fun setState(kClass: KClass<out RecyclerViewState>, notify: Boolean) {
        val state: RecyclerViewState =
            statesMap.keys.find { it::class == kClass }
                ?: throw RuntimeException("Unable to find state by class $kClass")

        for (entry: MutableMap.MutableEntry<RecyclerViewState, Boolean> in statesMap)
            entry.setValue(entry.key == state)

        if (notify) notifyDataSetChanged()
    }

    private val actualState: RecyclerViewState
        get() = statesMap.entries.find { it.value }?.key ?: ContentState

    protected open val currentState: RecyclerViewState
        get() = actualState

    final override fun getItemCount(): Int {
        val state: RecyclerViewState = actualState
        return when {
            state == ContentState -> getItemsCount()
            getItemsCount() == 0 && state.getViewStateType(ItemViewPosition.CONTENT)
                .let { it == ViewStateType.BOTH || it == ViewStateType.CONTENT } -> 1
            state.getViewStateType(ItemViewPosition.FOOTER)
                .let { it == ViewStateType.BOTH || it == ViewStateType.FOOTER } -> getItemsCount() + 1
            else -> getItemsCount()
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    final override fun getItemViewType(position: Int): Int =  when {
        isStatePosition(position) -> actualState.javaClass.hashCode()
        else -> getItemsViewType(position)
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val state: RecyclerViewState = actualState
        return when (viewType) {
            state.javaClass.hashCode() -> onCreateStateViewHolder(parent, state, viewType) as VH
            else -> onCreateItemsViewHolder(parent, viewType)
        }
    }

    final override fun onBindViewHolder(holder: VH, position: Int) =
        onBindViewHolder(holder, position, mutableListOf())

    final override fun onBindViewHolder(
        holder: VH, position: Int, payloads: MutableList<Any>
    ) = when {
        isStatePosition(position) -> onBindStateViewHolder(holder, actualState, position, payloads)
        else -> onBindItemsViewHolder(holder, position, payloads)
    }

    protected fun isStatePosition(position: Int): Boolean {
        val isStateForContent: Boolean = position == 0 && getItemsCount() == 0
        val isStateForFooter: Boolean = position == getItemsCount()
        return (isStateForContent || isStateForFooter) && actualState != ContentState
    }

    protected open fun getItemsViewType(position: Int): Int = 0

    abstract fun onCreateItemsViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun onCreateStateViewHolder(
        parent: ViewGroup, state: RecyclerViewState, viewType: Int
    ): RecyclerView.ViewHolder

    abstract fun onBindItemsViewHolder(
        holder: VH,
        position: Int, payloads: MutableList<Any> = mutableListOf()
    )

    abstract fun onBindStateViewHolder(
        holder: VH, state: RecyclerViewState,
        position: Int, payloads: MutableList<Any> = mutableListOf()
    )

    abstract fun getItemsCount(): Int

    object ContentState : RecyclerViewState() {
        override fun configureTypeByPosition(position: ItemViewPosition) =
            ViewStateType.BOTH
    }
}