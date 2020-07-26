package e.ustoz.uz.presentation.features.login

import android.os.Bundle
import android.view.View
import e.ustoz.uz.R
import e.ustoz.uz.databinding.FragmentLoginBinding
import e.ustoz.uz.support.delegate.app.dialog.error.ErrorDialogDelegate
import e.ustoz.uz.support.delegate.app.dialog.loading.LoadingDialogDelegate
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment @Inject constructor(
    private val presenterProvider: Provider<LoginPresenter>
) : MvpAppCompatFragment(R.layout.fragment_login), AuthenticateView {

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val loadingDialogDelegate by lazy { LoadingDialogDelegate(this) }
    private val errorDialogDelegate by lazy { ErrorDialogDelegate(this) }

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onLoadingLogin() {
    }

    override fun onSuccessLogin() {
    }

    override fun onFailureLogin(throwable: Throwable) {
    }
}
