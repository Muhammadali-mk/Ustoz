package e.ustoz.uz.presentation.features.main.features.common

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue
import androidx.annotation.AttrRes
import e.ustoz.uz.R

val Context.colorAccent: Int
    get() = getColorByAttr(R.attr.colorAccent)

val Context.colorPrimary: Int
    get() = getColorByAttr(R.attr.colorPrimary)

val Context.colorPrimaryDark: Int
    get() = getColorByAttr(R.attr.colorPrimaryDark)

private fun Context.getColorByAttr(@AttrRes resId: Int): Int {
    val typedArray: TypedArray = obtainStyledAttributes(TypedValue().data, intArrayOf(resId))
    val color: Int = typedArray.getColor(0, 0)
    typedArray.recycle()
    return color
}