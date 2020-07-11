package e.ustoz.uz.utils.app

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun DialogFragment.show(activity: AppCompatActivity) {
    val className: String = this::class.java.name
    with(activity.supportFragmentManager) {
        if (findFragmentByTag(className) == null)
            show(activity.supportFragmentManager, className)
    }
}

fun DialogFragment.show(fragment: Fragment) {
    val className: String = this::class.java.name
    with(fragment.childFragmentManager) {
        if (findFragmentByTag(className) == null)
            show(fragment.childFragmentManager, className)
    }
}