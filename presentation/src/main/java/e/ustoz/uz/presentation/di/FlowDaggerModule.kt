package e.ustoz.uz.presentation.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import e.ustoz.uz.presentation.FlowFragment
import e.ustoz.uz.presentation.features.login.LoginFragment
import e.ustoz.uz.presentation.features.main.di.MainScreenDaggerModule
import e.ustoz.uz.presentation.features.splash.SplashFragment
import e.ustoz.uz.support.dagger.fragment.FragmentKey

@Module(includes = [FlowDaggerModule.Binder::class, MainScreenDaggerModule::class])
object FlowDaggerModule {

    @Module
    interface Binder {

        @Binds
        @IntoMap
        @FragmentKey(FlowFragment::class)
        fun bindFlowFragment(fragment: FlowFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(LoginFragment::class)
        fun bindLoginFragment(fragment: LoginFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(SplashFragment::class)
        fun bindSplashFragment(fragment: SplashFragment): Fragment

    }
}