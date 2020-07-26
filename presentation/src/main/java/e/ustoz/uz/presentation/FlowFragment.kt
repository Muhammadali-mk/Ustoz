package e.ustoz.uz.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import e.ustoz.uz.R
import e.ustoz.uz.global.navigation.GlobalNavController
import e.ustoz.uz.support.delegate.navigation.NavControllerHolder
import e.ustoz.uz.support.moxy.MoxyMvpFragmentDelegate
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FlowFragment @Inject constructor(
    private val navControllerHolder: NavControllerHolder<GlobalNavController>
) : NavHostFragment(), FlowView {
    private val moxyMvpFragmentDelegate = MoxyMvpFragmentDelegate(this)

    @Inject
    @InjectPresenter
    lateinit var flowPresenter: FlowPresenter

    @ProvidePresenter
    fun provideFlowPresenter(): FlowPresenter = flowPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moxyMvpFragmentDelegate.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            flowPresenter.attachAndInit()
    }

    override fun onStart() {
        super.onStart()
        moxyMvpFragmentDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        moxyMvpFragmentDelegate.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        moxyMvpFragmentDelegate.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        moxyMvpFragmentDelegate.onStop()
    }

    override fun onDestroyView() {
        navControllerHolder.detachNavController()
        super.onDestroyView()
        moxyMvpFragmentDelegate.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        moxyMvpFragmentDelegate.onDestroy()
    }

    override fun onAttachGlobalNavController() =
        navControllerHolder
            .attachNavFragment(this)

    override fun onInitializeNavController(isLoginHasBeen: Boolean) {
        navController.let {
            it.navInflater.inflate(R.navigation.navigation_global).apply {
                startDestination =
                    R.id.navigation_global_main_fragment
                it.graph = this
            }
        }
    }
}