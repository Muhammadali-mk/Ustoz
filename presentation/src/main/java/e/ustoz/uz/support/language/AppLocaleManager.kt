package e.ustoz.uz.support.language

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import e.ustoz.data.datasource.preference.language.LanguagePreferenceSource
import java.util.*

class AppLocaleManager(
    private val languagePreferenceSource: LanguagePreferenceSource
) {
    fun attachBaseContext(application: Application, baseContext: Context): Context {
        application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks())
        return updateConfiguration(baseContext, languagePreferenceSource.currentLanguage.locale)
    }

    fun notifyConfigurationChanges(context: Context) =
        updateConfiguration(context, languagePreferenceSource.currentLanguage.locale)

    @Suppress("DEPRECATION")
    private fun updateConfiguration(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val configuration: Configuration =
            Configuration(resources.configuration)
                .apply { setLocale(locale); setLayoutDirection(locale) }

        return resources.updateConfiguration(configuration, resources.displayMetrics)
            .let { context }
    }

    private inner class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        private val map: HashMap<String, Locale> = hashMapOf()

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            notifyConfigurationChanges(activity)

            activity.window.decorView.layoutDirection =
                if (isRtl(Locale.getDefault())) View.LAYOUT_DIRECTION_RTL
                else View.LAYOUT_DIRECTION_LTR
        }

        override fun onActivityResumed(activity: Activity) {
            val cachedLocale: Locale = map[activity::class.java.name] ?: return
            if (cachedLocale != Locale.getDefault()) activity.recreate()
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityPaused(activity: Activity) {
            map[activity::class.java.name] = Locale.getDefault()
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (activity.isFinishing) map.remove(activity::class.java.name)
        }

        override fun onActivityStopped(activity: Activity) {
        }

        private fun isRtl(locale: Locale): Boolean {
            val languageSet: Set<String> =
                hashSetOf("ar", "dv", "fa", "ha", "he", "iw", "ji", "ps", "sd", "ug", "ur", "yi")
            return languageSet.contains(locale.language)
        }
    }
}

