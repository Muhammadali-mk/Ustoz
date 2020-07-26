package e.ustoz.uz.utils.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class SimpleRecyclerScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}