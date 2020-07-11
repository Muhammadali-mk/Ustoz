package e.ustoz.uz.support.delegate.view.recyclerview.state.states

import android.view.LayoutInflater
import android.view.ViewGroup
import e.ustoz.uz.databinding.ViewHolderStateEmptyBinding
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateView

class EmptyStateView() : RecyclerViewStateView() {
    private lateinit var binding: ViewHolderStateEmptyBinding

    override fun configureTypeByPosition(position: ItemViewPosition) = ViewStateType.CONTENT

    override fun onCreateView(inflater: LayoutInflater, root: ViewGroup) =
        ViewHolderStateEmptyBinding.inflate(inflater, root, false).root.apply {
            layoutParams = layoutParams.apply {
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }

    override fun onBind() {
    }
}