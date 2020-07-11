package uz.anotomica.app.presentation.support.delegate.view.recyclerview.state.states

import android.view.LayoutInflater
import android.view.ViewGroup
import e.ustoz.uz.databinding.ViewHolderStateErrorRetryBinding
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateView

class ErrorRetryStateView(private val clickListener: () -> Unit) : RecyclerViewStateView() {
    private lateinit var binding: ViewHolderStateErrorRetryBinding

    override fun configureTypeByPosition(position: ItemViewPosition) = ViewStateType.BOTH

    override fun onCreateView(inflater: LayoutInflater, root: ViewGroup) =
        ViewHolderStateErrorRetryBinding.inflate(inflater, root, false).also {
            binding = it
        }.root.apply {
            layoutParams = layoutParams.apply {
                height =
                    if (itemViewPosition == ItemViewPosition.CONTENT)
                        ViewGroup.LayoutParams.MATCH_PARENT
                    else ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }

    override fun onBind() {
        binding.retryButton.setOnClickListener { clickListener.invoke() }
    }
}