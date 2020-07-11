package e.ustoz.uz.global.di

import dagger.Component
import e.ustoz.data.manager.coroutine.CoroutineContextManager
import e.ustoz.data.provider.repository.DataRepositoryProvider
import e.ustoz.uz.application.di.ApplicationDaggerComponent
import e.ustoz.uz.global.GlobalActivity
import e.ustoz.uz.global.navigation.GlobalNavController
import e.ustoz.uz.support.delegate.navigation.NavControllerHolder

@GlobalScope
@Component(
    dependencies = [ApplicationDaggerComponent::class],
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModules::class
    ]
)
interface GlobalDaggerComponent : DataRepositoryProvider {

    fun coroutineContextManager(): CoroutineContextManager

    fun globalNavController(): GlobalNavController

    fun navControllerHolder(): NavControllerHolder<GlobalNavController>

    fun inject(activity: GlobalActivity)

    @Component.Factory
    interface Factory {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent
    }

    companion object {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent =
            DaggerGlobalDaggerComponent
                .factory()
                .create(component)
    }
}