package e.ustoz.uz.view.recyclerview.state

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import e.ustoz.uz.R
import e.ustoz.uz.view.recyclerview.state.wrapper.RecyclerViewStateView

object StateViews {

    open class EmptyStateView : RecyclerViewStateView() {

        override fun configureTypeByPosition(position: ItemViewPosition) =
            ViewStateType.CONTENT

        override fun onCreateView(inflater: LayoutInflater, root: ViewGroup): View {
            val view: View =
                inflater.inflate(R.layout.view_holder_state_empty, root, false)

            view.layoutParams = view.layoutParams.apply {
                height =
                    if (itemViewPosition == ItemViewPosition.CONTENT)
                        ViewGroup.LayoutParams.MATCH_PARENT
                    else ViewGroup.LayoutParams.WRAP_CONTENT
            }

            return view
        }
    }

    class ErrorStateView(private val clickListener: () -> Unit) : RecyclerViewStateView() {
        private lateinit var retryButton: MaterialButton
        private var throwable: Throwable? = null

        override fun configureTypeByPosition(position: ItemViewPosition) =
            ViewStateType.BOTH

        override fun onCreateView(inflater: LayoutInflater, root: ViewGroup): View {
            val view: View =
                inflater.inflate(R.layout.view_holder_state_error_retry, root, false)

            view.layoutParams = view.layoutParams.apply {
                height =
                    if (itemViewPosition == ItemViewPosition.CONTENT)
                        ViewGroup.LayoutParams.MATCH_PARENT
                    else ViewGroup.LayoutParams.WRAP_CONTENT
            }

            retryButton = view.findViewById(android.R.id.button1)

            return view
        }

        fun setThrowable(throwable: Throwable) {
            this.throwable = throwable
        }

        override fun onBind() {
            retryButton.setOnClickListener { clickListener.invoke() }
        }
    }

    open class LoadingStateView : RecyclerViewStateView() {

        override fun configureTypeByPosition(position: ItemViewPosition) =
            ViewStateType.BOTH

        override fun onCreateView(inflater: LayoutInflater, root: ViewGroup): View {
            val view: View =
                inflater.inflate(R.layout.view_holder_state_loading, root, false)

            view.layoutParams = view.layoutParams.apply {
                height =
                    if (itemViewPosition == ItemViewPosition.CONTENT)
                        ViewGroup.LayoutParams.MATCH_PARENT
                    else ViewGroup.LayoutParams.WRAP_CONTENT
            }

            return view
        }
    }
}