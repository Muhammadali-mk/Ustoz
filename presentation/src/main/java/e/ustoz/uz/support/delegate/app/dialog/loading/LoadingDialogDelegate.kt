package uz.anotomica.app.presentation.support.delegate.app.dialog.loading

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import uz.anotomica.app.presentation.R
import uz.anotomica.app.presentation.support.delegate.app.dialog.DialogDelegate

class LoadingDialogDelegate(
    context: Context, lifecycleOwner: LifecycleOwner?
) : DialogDelegate(context, lifecycleOwner) {

    constructor(activity: AppCompatActivity) : this(activity.baseContext, activity)

    constructor(fragment: Fragment) : this(fragment.requireContext(), fragment)

    private var isInitialized: Boolean = false

    init {
        @Suppress("DEPRECATION")
        newBuilder {
            setView(R.layout.layout_dialog_loading)
            setCancelable(false)
        }

        isInitialized = true
    }

    @Deprecated("", ReplaceWith("throw UnsupportedOperationException()"))
    override fun dialog(dialog: AlertDialog.() -> Unit) =
        throw UnsupportedOperationException()

    @Deprecated("")
    override fun newBuilder(builder: AlertDialog.Builder.() -> Unit) {
        if (!isInitialized) super.newBuilder(builder)
        else throw UnsupportedOperationException()
    }
}