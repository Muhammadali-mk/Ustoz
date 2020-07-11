package uz.anotomica.app.presentation.support.delegate.app.dialog.error

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import uz.anotomica.app.presentation.BuildConfig
import uz.anotomica.app.presentation.R
import uz.anotomica.app.presentation.databinding.ViewErrorDialogDelegateBinding
import uz.anotomica.app.presentation.support.delegate.app.dialog.DialogDelegate

class ErrorDialogDelegate(
    context: Context, lifecycleOwner: LifecycleOwner?
) : DialogDelegate(context, lifecycleOwner) {

    constructor(fragment: Fragment) : this(fragment.requireContext(), fragment)

    private var isInitialized: Boolean = false

    private lateinit var binding: ViewErrorDialogDelegateBinding

    init {
        @Suppress("DEPRECATION")
        newBuilder {
            setTitle(R.string.delegate_error_dialog_title)
            setPositiveButton(android.R.string.ok) { _, _ -> dismiss() }
            val inflater = LayoutInflater.from(context)
            binding = ViewErrorDialogDelegateBinding.inflate(inflater, null, false)
            setView(binding.root)
        }

        isInitialized = true
    }

    @Deprecated("", ReplaceWith("throw UnsupportedOperationException()"))
    override fun show() =
        throw UnsupportedOperationException()

    @Suppress("DEPRECATION")
    fun show(throwable: Throwable?) =
        dialog { setErrorMessage(throwable); show() }

    @Deprecated("")
    override fun newBuilder(builder: AlertDialog.Builder.() -> Unit) {
        if (!isInitialized) super.newBuilder(builder)
        else throw UnsupportedOperationException()
    }

    private fun setErrorMessage(throwable: Throwable?) {
        var message: String? = ""
        var errorType: String? = ""
        val version = context?.getString(
            R.string.title_app_version,
            BuildConfig.VERSION_NAME
        )
        when (throwable) {

//            is EmptyEntityException -> {
//                errorType = context?.getString(
//                    R.string.delegate_error_dialog_message_type,
//                    getClassName(throwable)
//                )
//                message = context?.getString(R.string.delegate_error_dialog_empty_database_message)
//            }
            else -> when {
                throwable != null -> {
                    errorType = context?.getString(
                        R.string.delegate_error_dialog_message_type,
                        getClassName(throwable)
                    )
                    message = context?.getString(R.string.delegate_error_dialog_unknown_message)
                }
                else -> {
                    errorType = null
                    message = context?.getString(R.string.delegate_error_dialog_message)
                }
            }
        }

        binding.apply {
            errorDialogTitleTextView.text = message
            errorDialogMessageTextView.text =
                if (errorType.isNullOrEmpty()) "$version"
                else "$errorType\n$version"
        }
    }

    private fun getClassName(throwable: Throwable): String =
        throwable.javaClass.simpleName.replace("Exception", "")

}