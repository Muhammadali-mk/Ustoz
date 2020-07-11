package e.ustoz.uz.global.di

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import e.ustoz.uz.global.di.GlobalDaggerModule.Binder
import e.ustoz.uz.global.di.GlobalDaggerModule.Provider
import e.ustoz.uz.global.navigation.GlobalNavController
import e.ustoz.uz.support.dagger.fragment.ProviderFragmentFactory
import e.ustoz.uz.support.delegate.navigation.NavControllerHolder

@Module(includes = [Binder::class, Provider::class])
object GlobalDaggerModule {

    @Module
    interface Binder {

        @Binds
        @GlobalScope
        fun bindProviderFragmentFactory(factory: ProviderFragmentFactory): FragmentFactory
    }

    @Module
    object Provider {
        @JvmStatic
        @Provides
        @GlobalScope
        fun provideNavControllerHolder(): NavControllerHolder<GlobalNavController> =
            NavControllerHolder(GlobalNavController())

        @JvmStatic
        @Provides
        @GlobalScope
        fun provideGlobalNavController(
            delegate: NavControllerHolder<GlobalNavController>
        ): GlobalNavController = delegate.controller
    }
}