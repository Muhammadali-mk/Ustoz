package uz.anotomica.app.presentation.utils.keyboard

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object SoftKeyboardCompat {

    fun hide(activity: Activity) {
        if (activity.currentFocus != null)
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
                hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
            }
    }

    fun show(activity: Activity) {
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
            toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }
}