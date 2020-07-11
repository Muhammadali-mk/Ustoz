package e.ustoz.uz.view.recyclerview.state.wrapper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.ustoz.uz.view.recyclerview.state.RecyclerViewState

abstract class RecyclerViewStateView : RecyclerViewState() {

    abstract override fun configureTypeByPosition(position: ItemViewPosition): ViewStateType

    abstract fun onCreateView(inflater: LayoutInflater, root: ViewGroup): View

    open fun onBind() {
    }
}