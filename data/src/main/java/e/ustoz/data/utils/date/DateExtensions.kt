package e.ustoz.data.utils.date

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)