package e.ustoz.uz.global

import android.os.Bundle
import e.ustoz.uz.databinding.ActivityGlobalBinding
import e.ustoz.uz.global.di.GlobalDaggerComponent
import e.ustoz.uz.support.dagger.fragment.ProviderFragmentFactory
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class GlobalActivity : MvpAppCompatActivity(), IHasComponent<GlobalDaggerComponent>,
    GlobalView {

    @Inject
    @InjectPresenter
    lateinit var globalPresenter: GlobalPresenter

    @ProvidePresenter
    fun provideGlobalPresenter(): GlobalPresenter = globalPresenter

    private val binding by lazy { ActivityGlobalBinding.inflate(layoutInflater) }

    override fun getComponent(): GlobalDaggerComponent =
        GlobalDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @Inject
    fun setFragmentFactory(fragmentFactory: ProviderFragmentFactory) {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}