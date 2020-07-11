package e.ustoz.uz.presentation.features.splash

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import dagger.Lazy
import e.ustoz.uz.R
import e.ustoz.uz.databinding.FragmentSplashBinding
import e.ustoz.uz.utils.app.onBackPressedDispatcher
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.anotomica.app.presentation.presentation.features.splash.SplashView
import uz.anotomica.app.presentation.support.delegate.app.dialog.DialogDelegate
import javax.inject.Inject

class SplashFragment @Inject constructor() : MvpAppCompatFragment(R.layout.fragment_splash),
    SplashView {

    @Inject
    lateinit var lazyPresenter: Lazy<SplashPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val dialogDelegate by lazy { DialogDelegate(this) }

    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        presenter.apply {
            getUpdateState()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onBackPressedDispatcher.addCallback(this)
        { requireActivity().finish() }
    }


    override fun onLoading() {
        binding.apply {
            splashProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onSuccess() {}

    override fun onFailure(throwable: Throwable) {

    }
}
