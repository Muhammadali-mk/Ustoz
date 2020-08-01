package e.ustoz.uz.presentation.features.main.features.delegate

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import e.ustoz.uz.R
import e.ustoz.uz.support.delegate.view.recyclerview.state.RecyclerViewStateDelegate
import e.ustoz.uz.view.recyclerview.RecyclerViewAdapter
import e.ustoz.uz.view.recyclerview.ViewHolderItemBinder
import e.ustoz.uz.databinding.ViewHolderOnlineCourseBinding as ViewBinding


internal class OnlineLessonsListRecyclerViewDelegate(
    target: LifecycleOwner?,
    private val listener: (OnlineLessonResponse) -> Unit
) : RecyclerViewStateDelegate<OnlineLessonResponse>(target) {

    override fun getAdapter(): RecyclerViewAdapter<OnlineLessonResponse, *> =
        Adapter {
            listener.invoke(it)
        }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    override fun getItemDecoration(): Array<RecyclerView.ItemDecoration>? =
        arrayOf(MarginItemDecoration())

    private class Adapter(
        private val listener: (OnlineLessonResponse) -> Unit
    ) : RecyclerViewAdapter<OnlineLessonResponse, RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater: LayoutInflater = LayoutInflater.from(parent.context)
            return HeadingViewHolder(
                ViewBinding.inflate(inflater, parent, false)
            )
        }

        override fun getId(element: OnlineLessonResponse): Any = element.id

        inner class HeadingViewHolder(
            private val binding: ViewBinding
        ) : RecyclerView.ViewHolder(binding.root), ViewHolderItemBinder<OnlineLessonResponse> {
            override fun onBind(element: OnlineLessonResponse) {
                binding.apply {
                    itemView.setOnClickListener { listener.invoke(element) }
                 //   lessonImageSimpleDraweeView.setImageURI(element.imageUrl)
                    lessonNameTextView.text = element.title
                }
            }
        }
    }

    private class MarginItemDecoration : RecyclerView.ItemDecoration() {

        @Volatile
        private var spaces: Pair<Int, Int>? = null

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            with(outRect) {
                val childCount = state.itemCount
                val position = parent.getChildAdapterPosition(view) + 1
                val isFirstItem: Boolean = position < 2
                val isLastItem: Boolean = childCount == position

                getSpaces(parent.context).let {
                    top = it.first
                    left = if (isFirstItem) it.second else it.first
                    right = if (isLastItem) it.second else it.first
                    bottom = it.second
                }
            }
        }

        private fun getSpaces(context: Context): Pair<Int, Int> {
            return if (spaces != null) checkNotNull(spaces)
            else synchronized(this) {
                return if (spaces != null) checkNotNull(spaces)
                else {
                    val spaceIn = context.resources.getDimensionPixelSize(R.dimen.space_6)
                    val spaceOut = context.resources.getDimensionPixelSize(R.dimen.space_16)
                    spaces = Pair(first = spaceIn, second = spaceOut)
                    checkNotNull(spaces)
                }
            }
        }
    }
}