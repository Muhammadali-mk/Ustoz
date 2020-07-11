package e.ustoz.data.utils.text

object TextUtils {

    fun removeAllCharacters(value: String) =
        value.replace("[^0-9,.]+".toRegex(), "")

    fun removeAllWhiteSpaces(value: String) =
        value.replace("\\s".toRegex(), "")
}