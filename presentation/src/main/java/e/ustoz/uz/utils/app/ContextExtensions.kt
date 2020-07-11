package e.ustoz.uz.utils.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlin.reflect.KClass

fun Context.startActivityWithClearTask(
    kClass: KClass<out Activity>,
    bundle: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, kClass.java).putExtras(Bundle().also(bundle))
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(intent)
}