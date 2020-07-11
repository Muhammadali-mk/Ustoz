package e.ustoz.uz.support.delegate.view.recyclerview

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.support.delegate.view.recyclerview.state.RecyclerViewStateDelegate

abstract class RecyclerViewEndlessScrollDelegate<T : Any>(
    target: LifecycleOwner?,
    private val endlessListener: (page: Int, totalItemsCount: Int) -> Unit
) : RecyclerViewStateDelegate<T>(target) {


    //    override fun onSetupRecyclerView(manager: RecyclerView.LayoutManager?) {
    fun onSetupRecyclerView(manager: RecyclerView.LayoutManager?) {
        view?.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(checkNotNull(manager)) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                endlessListener.invoke(page, totalItemsCount)
            }
        })
    }


}