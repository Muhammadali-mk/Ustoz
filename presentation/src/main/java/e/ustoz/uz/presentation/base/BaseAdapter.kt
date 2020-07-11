package e.ustoz.uz.presentation.base

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, BaseVH : RecyclerView.ViewHolder>(
    var context: Context,
    protected var items: ArrayList<T>? = null
) : RecyclerView.Adapter<BaseVH>() {


    protected var inflater: LayoutInflater = LayoutInflater.from(context)

    var isFooterEnabled = false

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
//        return null
//    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        //        ShimmerHolderDefault shimmerHolderDefault = (ShimmerHolderDefault) holder;
        //        ((ShimmerLayout) shimmerHolderDefault.itemParent).startShimmerAnimation();
    }


    override fun getItemCount(): Int {
        return if (isFooterEnabled)
            1 + (items?.size ?: 0)
        else
            items?.size ?: 0
    }

    fun getItemsSize(): Int {
        return items?.size ?: 0
    }

    fun hasItems(): Boolean {
        return items != null && items?.isNotEmpty() == true
    }

    fun getItemByPosition(position: Int): T {
        return if (position < items?.size ?: -1)
            items?.get(position)!!
        else throw Throwable("invalid position")
    }

    fun updateData(newItems: ArrayList<T>?) {
        if (items == null)
            items = ArrayList()
        if (newItems != null)
            items?.addAll(newItems)
        notifyDataSetChanged()
    }

    fun resetData(newItems: ArrayList<T>) {
        if (items == null)
            items = ArrayList()
        else
            items?.clear()
        items?.addAll(newItems)
        notifyDataSetChanged()
    }

    fun clearData() {
        if (items != null) {
            items?.clear()
            notifyDataSetChanged()
        }
    }

    fun getItems(): List<T>? {
        return items
    }
}
