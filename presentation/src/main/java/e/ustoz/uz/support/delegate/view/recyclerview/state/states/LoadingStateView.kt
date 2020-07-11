package uz.anotomica.app.presentation.support.delegate.view.recyclerview.state.states

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import e.ustoz.uz.databinding.ViewHolderStateLoadingBinding
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateView

object LoadingStateView : RecyclerViewStateView() {

    override fun configureTypeByPosition(position: ItemViewPosition) = ViewStateType.BOTH

    override fun onCreateView(inflater: LayoutInflater, root: ViewGroup) =
        ViewHolderStateLoadingBinding.inflate(inflater, root, false).root.apply {
            layoutParams = layoutParams.apply {
                height =
                    if (itemViewPosition == ItemViewPosition.CONTENT) MATCH_PARENT
                    else WRAP_CONTENT
            }
        }

    override fun onBind() = Unit
}