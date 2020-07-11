package e.ustoz.data.utils.text

import java.math.BigDecimal

fun BigDecimal.toStringCompat(): String {
    val completeBigDecimal = this.divide(BigDecimal(100))
    val charsCountAfterDot = completeBigDecimal.toString().let { string ->
        string.lastIndexOf(".")
            .let {
                return@let if (it > -1) ((string.length - 1) - (it - 1) - 1)
                else 0
            }
    }

    var suffix = ""
    repeat(charsCountAfterDot) { suffix += "0" }
    return String.format("%,.${charsCountAfterDot}f", completeBigDecimal)
}