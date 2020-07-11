package e.ustoz.uz.view.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<T : Any, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {
    private val mutableList: MutableList<T> = arrayListOf()

    protected val list: List<T>
        get() = mutableList

    val isEmpty: Boolean
        get() = mutableList.isEmpty()

    val isNotEmpty: Boolean
        get() = !isEmpty

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: VH, position: Int) {
        (holder as? ViewHolderItemBinder<T>)?.onBind(mutableList[position])
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewDetachedFromWindow(holder: VH) {
        (holder as? ViewHolderItemBinder<T>)?.onUnbind()
    }

    override fun getItemCount(): Int =
        mutableList.size

    open fun set(collection: Collection<T>) =
        mutableList.let { clear(); addAll(collection) }

    open fun add(element: T) {
        mutableList.add(element)
        notifyItemInserted(mutableList.size)
    }

    open fun add(position: Int, element: T) {
        mutableList.add(position, element)
        notifyItemInserted(position)
    }

    open fun addAll(collection: Collection<T>) {
        collection.forEach { element -> this.mutableList.add(element) }
        val newSize: Int = collection.size
        val oldSize: Int = this.mutableList.size - newSize
        notifyItemRangeInserted(oldSize, newSize)
    }

    open fun remove(element: T) {
        val position: Int = getPosition(element)
        if (position >= 0) {
            mutableList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun update(element: T) {
        val position: Int = getPosition(element)
        if (position >= 0) {
            mutableList.apply { removeAt(position); add(position, element) }
            notifyItemChanged(position)
        }
    }

    open fun update(position: Int, element: T) {
        val currentPosition: Int = getPosition(element)
        if (currentPosition >= 0) {
            mutableList.apply { removeAt(currentPosition); add(position, element) }
            notifyItemChanged(position)
        }
    }

    @Suppress("SpellCheckingInspection")
    open fun upsertAll(collection: Collection<T>) {
        val adapterCollection: MutableList<T> = list.toMutableList()
        val upsertCollection: MutableList<T> = mutableListOf()
        val removeCollection: MutableList<T> = mutableListOf()

        collection.indices.forEach {
            val position: Int = it
            val element: T = collection.elementAt(it)
            var isContainsInAdapter = false

            for (adapterElement: T in list) {
                if (getId(element) == getId(adapterElement)) {
                    isContainsInAdapter = true
                    break
                }
            }

            if (!isContainsInAdapter) {
                upsertCollection.add(element)
                add(position, element)
            } else {
                upsertCollection.add(element)
                update(position, element)
            }
        }

        adapterCollection.forEach { adapterElement ->
            upsertCollection.forEach { item ->
                if (getId(adapterElement) != getId(item))
                    removeCollection.add(adapterElement)
            }
        }

        adapterCollection.let { it ->
            it.removeAll(removeCollection)
            it.forEach { remove(it) }
        }
    }

    open fun clear() {
        mutableList.clear()
        notifyDataSetChanged()
    }

    open fun getPosition(element: T): Int {
        val id: Any = getId(element)
        var position: Int = -1

        for (i: Int in mutableList.indices) {
            val currentElement: T = mutableList[i]
            if (getId(currentElement) == id) {
                position = i
                break
            }
        }

        return position
    }

    open fun getElement(position: Int): T? =
        if (position > 0 && position < mutableList.size)
            mutableList[position]
        else null

    open fun containsInAdapter(element: T): Boolean =
        getPosition(element) != -1

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    protected abstract fun getId(element: T): Any
}