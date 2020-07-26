package e.ustoz.uz.presentation.features.main.features.material

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import dagger.Lazy
import e.ustoz.uz.R
import e.ustoz.uz.presentation.features.main.features.common.colorAccent
import e.ustoz.uz.presentation.features.main.features.common.setTint
import e.ustoz.uz.support.delegate.view.ToolbarDelegate
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import e.ustoz.uz.databinding.FragmentFeatureUserMaterialsBinding as ViewBinding

class MaterialsFragment @Inject constructor() : MvpAppCompatFragment(R.layout.fragment_feature_user_materials)
    , MaterialsView, Toolbar.OnMenuItemClickListener {

    @Inject
    internal lateinit var lazyPresenter: Lazy<MaterialsPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private lateinit var binding: ViewBinding
    private val toolbarDelegate by lazy { ToolbarDelegate(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ViewBinding.bind(view)
        binding.apply {
            toolbarDelegate.apply {
                onCreate(toolbar, savedInstanceState)
                inflateMenu(R.menu.menu_main_screen_toolbar, this@MaterialsFragment)
                findMenuItemById(R.id.search_menu_item)?.setTint(requireContext().colorAccent)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.search_menu_item -> testMehodToast("SEARCH").let { true }
        R.id.user_info_menu_item -> testMehodToast("USER INFO").let { true }
        else -> false
    }

    private fun testMehodToast(value: String) {
        Toast.makeText(requireContext(), "$value MENU CLICKED", Toast.LENGTH_LONG).show()
    }
}