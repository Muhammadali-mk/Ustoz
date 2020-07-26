package e.ustoz.uz.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import e.ustoz.uz.application.di.ApplicationDaggerComponent
import e.ustoz.uz.support.application.ApplicationLifecycleSupportCallbacks
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import kotlin.properties.Delegates

class Application : Application(),
    IHasComponent<ApplicationDaggerComponent>
//    , WorkConfiguration.Provider
{

//    @Inject lateinit var appLocaleManager: AppLocaleManager
//    @Inject lateinit var providerWorkerFactory: ProviderWorkerFactory

//    private val workManagerDelegate: WorkManagerDelegate = WorkManagerDelegate()

    private var pureContext: Context by Delegates.notNull()

    override fun getComponent(): ApplicationDaggerComponent =
        ApplicationDaggerComponent.create(pureContext)

//    override fun getWorkManagerConfiguration(): WorkConfiguration =
//        WorkConfiguration.Builder()
//            .setWorkerFactory(providerWorkerFactory)
//            .build()

    override fun attachBaseContext(base: Context) {
        pureContext = base
        XInjectionManager.let { it.init(this); it.bindComponent(this).inject(this) }
//        super.attachBaseContext(appLocaleManager.attachBaseContext(this, base))
        super.attachBaseContext(base)
    }

    init {
        Log.wtf("uztoz_data", "application created")
    }

    override fun onCreate() {
        super.onCreate()
//        workManagerDelegate.enqueue(this)
        Fresco.initialize(this)
        ApplicationLifecycleSupportCallbacks(this)
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
//        appLocaleManager.notifyConfigurationChanges(this)
    }
}