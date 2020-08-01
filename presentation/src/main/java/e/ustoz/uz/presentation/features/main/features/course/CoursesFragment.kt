package e.ustoz.uz.presentation.features.main.features.course

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import e.ustoz.data.model.teacher_information.online_lesson.OnlineLessonResponse
import e.ustoz.data.model.user_course.UserCourseResponse
import e.ustoz.uz.R
import e.ustoz.uz.databinding.FragmentFeatureUserCoursesBinding
import e.ustoz.uz.presentation.features.main.features.common.colorAccent
import e.ustoz.uz.presentation.features.main.features.common.setTint
import e.ustoz.uz.presentation.features.main.features.delegate.OnlineLessonsListRecyclerViewDelegate
import e.ustoz.uz.presentation.features.main.features.delegate.UserCourceRecyclerViewDelegate
import e.ustoz.uz.support.delegate.app.dialog.loading.LoadingDialogDelegate
import e.ustoz.uz.support.delegate.view.ToolbarDelegate
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CoursesFragment @Inject constructor() :
    MvpAppCompatFragment(R.layout.fragment_feature_user_courses),
    CoursesView, Toolbar.OnMenuItemClickListener {
    @Inject
    internal lateinit var lazyPresenter: Lazy<CoursesPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val binding: FragmentFeatureUserCoursesBinding by viewBinding()
    private val loadingDialogDelegate by lazy { LoadingDialogDelegate(this) }
    private val toolbarDelegate by lazy { ToolbarDelegate(this) }
    private val onlineLessonsListRecyclerViewDelegate by lazy {
        OnlineLessonsListRecyclerViewDelegate(this) {
            Toast.makeText(
                requireContext(),
                "Clicked ${it.title}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private val userCourseRecyclerViewDelegate by lazy {
        UserCourceRecyclerViewDelegate(this) { }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbarDelegate.apply {
                onCreate(toolbar, savedInstanceState)
                inflateMenu(R.menu.menu_main_screen_toolbar, this@CoursesFragment)
                findMenuItemById(R.id.search_menu_item)?.setTint(requireContext().colorAccent)
            }
            onlineLessonsListRecyclerViewDelegate
                .onCreate(onlineLessonsRecyclerViewHorizontal, savedInstanceState)
        }
        userCourseRecyclerViewDelegate
            .onCreate(binding.recyclerViewUserCourseList, savedInstanceState)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.search_menu_item -> testMehodToast("SEARCH").let { true }
        R.id.user_info_menu_item -> presenter.navigateToUserInfo().let { true }
        else -> false
    }

    /// TODO: 7/31/2020 dobavleno vremenno
    private fun testMehodToast(value: String) {
        Toast.makeText(requireContext(), "$value MENU CLICKED", Toast.LENGTH_LONG).show()
    }

    override fun onLoading() {
        loadingDialogDelegate.show()
    }

    override fun onSuccess(list: List<OnlineLessonResponse>) {
        loadingDialogDelegate.dismiss()
        onlineLessonsListRecyclerViewDelegate.apply {
            clear(); onSuccess(list)
        }
    }

    override fun onFailure(throwable: Throwable) {
        loadingDialogDelegate.dismiss()
    }

    override fun onSuccessUserCources(list: List<UserCourseResponse>) {
        loadingDialogDelegate.dismiss()
        Log.wtf("LIST_USER_COURSE","${list.toString()}")
        userCourseRecyclerViewDelegate.apply {
            clear(); onSuccess(list)
        }
    }
}