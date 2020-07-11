package e.ustoz.uz.presentation.features.main.features.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import e.ustoz.uz.R
import e.ustoz.uz.databinding.FragmentHomeBinding
import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider


class HomeFragment @Inject constructor(
    presenterProvider: Provider<HomePresenter>
) : MvpAppCompatFragment(R.layout.fragment_home), HomeView,
    Toolbar.OnMenuItemClickListener {

    private lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }
}