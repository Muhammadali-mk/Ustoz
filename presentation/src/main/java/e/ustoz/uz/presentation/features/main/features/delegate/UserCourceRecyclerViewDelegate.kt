package e.ustoz.uz.presentation.features.main.features.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.data.model.user_course.UserCourseResponse
import e.ustoz.uz.support.delegate.view.recyclerview.state.RecyclerViewStateDelegate
import e.ustoz.uz.view.recyclerview.RecyclerViewAdapter
import e.ustoz.uz.view.recyclerview.ViewHolderItemBinder
import e.ustoz.uz.databinding.ViewHolderUserCoucseBinding as ViewBinding

internal class UserCourceRecyclerViewDelegate(
    target: LifecycleOwner?,
    private val listener: (UserCourseResponse) -> Unit
) : RecyclerViewStateDelegate<UserCourseResponse>(target) {

    override fun getAdapter(): RecyclerViewAdapter<UserCourseResponse, *> = Adapter {
        listener.invoke(it)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context)

    private class Adapter(
        private val listener: (UserCourseResponse) -> Unit
    ) : RecyclerViewAdapter<UserCourseResponse, RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater: LayoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(
                ViewBinding.inflate(inflater, parent, false)
            )
        }


        override fun getId(element: UserCourseResponse): Any = element.courseId

        inner class ViewHolder(
            private val binding: ViewBinding
        ) : RecyclerView.ViewHolder(binding.root), ViewHolderItemBinder<UserCourseResponse> {
            override fun onBind(element: UserCourseResponse) {
                binding.apply {
                    if (adapterPosition == 0) {
                        dividerTop.visibility = View.VISIBLE
                    }
                    itemView.setOnClickListener { listener.invoke(element) }
                    userCourrseCountTextView.text =
                        "${element.courceProgress}/${element.lessonCount}"
                    userCourseNameTextView.text = element.courseTitle
                    userCourseAuthorNameTextView.text = element.authorName
                }
            }
        }
    }
}