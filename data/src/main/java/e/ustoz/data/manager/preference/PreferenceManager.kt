package e.ustoz.data.manager.preference

internal interface PreferenceManager {

    fun getBoolean(tag: String, defaultValue: Boolean): Boolean

    fun getInt(tag: String, defaultValue: Int): Int

    fun getLong(tag: String, defaultValue: Long): Long

    fun getString(tag: String, defaultValue: String): String

    fun getStringList(tag: String): List<String>

    fun setBoolean(tag: String, value: Boolean)

    fun setInt(tag: String, value: Int)

    fun setLong(tag: String, value: Long)

    fun setString(tag: String, value: String)

    fun setStringList(tag: String, values: List<String>)

    fun clear(tag: String)

    fun clearAll()
}