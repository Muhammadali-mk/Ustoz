package e.ustoz.uz.presentation.features.main.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import e.ustoz.uz.global.di.GlobalScope
import e.ustoz.uz.presentation.features.main.MainScreenFragment
import e.ustoz.uz.presentation.features.main.features.home.HomeFragment
import e.ustoz.uz.support.dagger.fragment.FragmentKey
import uz.anotomica.app.presentation.presentation.features.main.route.MainScreenRouteController

@Module(includes = [MainScreenDaggerModule.Binder::class])
object MainScreenDaggerModule {

    @JvmStatic
    @Provides
    @GlobalScope
    fun provideRouteController(): MainScreenRouteController = MainScreenRouteController()

    @Module
    interface Binder {

        @Binds
        @IntoMap
        @FragmentKey(HomeFragment::class)
        fun bindHomeFragment(fragment: HomeFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(MainScreenFragment::class)
        fun bindMainFragment(fragment: MainScreenFragment): Fragment
    }
}