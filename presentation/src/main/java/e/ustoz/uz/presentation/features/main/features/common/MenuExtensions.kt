package e.ustoz.uz.presentation.features.main.features.common

import android.view.MenuItem
import androidx.annotation.ColorInt

fun MenuItem.setTint(@ColorInt resId: Int) {
    icon?.let {
        it.mutate()
        it.setTint(resId)
    }
}