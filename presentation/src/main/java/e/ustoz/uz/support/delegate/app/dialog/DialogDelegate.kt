package e.ustoz.uz.support.delegate.app.dialog

import android.content.Context
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import e.ustoz.uz.support.lifecycle.LifecycleDelegate
import java.lang.ref.WeakReference

open class DialogDelegate constructor(
    context: Context, lifecycleOwner: LifecycleOwner?
) : LifecycleDelegate(lifecycleOwner) {

    constructor(activity: AppCompatActivity) : this(activity.baseContext, activity)

    constructor(fragment: Fragment) : this(fragment.requireContext(), fragment)

    private val contextReference: WeakReference<Context> = WeakReference(context)
    private var alertDialog: AlertDialog? = null

    protected val context: Context? get() = contextReference.get()

    init {
        alertDialog = MaterialAlertDialogBuilder(context).create()
    }

    open fun dialog(dialog: AlertDialog.() -> Unit) {
        alertDialog?.apply(dialog)
    }

    open fun newBuilder(builder: AlertDialog.Builder.() -> Unit) {
        alertDialog = AlertDialog.Builder(checkNotNull(context)).apply(builder).create()
    }

    open fun show() {
        alertDialog?.show()
    }

    open fun dismiss() {
        alertDialog?.dismiss()
    }

    @CallSuper
    override fun onDestroy() {
        alertDialog?.let { it.setOnCancelListener(null); it.setOnDismissListener(null) }
        alertDialog?.let {
            if (it.isShowing) {
                it.cancel(); it.dismiss()
            }
        }
        alertDialog = null
        super.onDestroy()
    }
}