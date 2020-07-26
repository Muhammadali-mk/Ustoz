package e.ustoz.uz.presentation.features.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import e.ustoz.uz.R
import e.ustoz.uz.databinding.FragmentMainScreenBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class MainScreenFragment @Inject constructor(
    presenterProvider: Provider<MainScreenPresenter>
) : MvpAppCompatFragment(R.layout.fragment_main_screen), MainScreenView {

    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var binding: FragmentMainScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        binding.bottomNavigationView.apply {

            itemIconTintList=null
            setupWithNavController(navController)
        }
    }
}