package e.ustoz.data.manager.preference

import android.content.SharedPreferences

internal class PreferenceManagerImpl(
    private val sharedPreferences: SharedPreferences
) : PreferenceManager {

    override fun getBoolean(tag: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(tag, defaultValue)

    override fun getInt(tag: String, defaultValue: Int): Int =
        sharedPreferences.getInt(tag, defaultValue)

    override fun getLong(tag: String, defaultValue: Long): Long =
        sharedPreferences.getLong(tag, defaultValue)

    override fun getString(tag: String, defaultValue: String): String =
        sharedPreferences.getString(tag, defaultValue) ?: ""

    override fun getStringList(tag: String): List<String> =
        sharedPreferences.getStringSet(tag, setOf())?.toList() ?: listOf()

    override fun setBoolean(tag: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(tag, value).apply()

    override fun setInt(tag: String, value: Int) =
        sharedPreferences.edit().putInt(tag, value).apply()

    override fun setStringList(tag: String, values: List<String>) =
        sharedPreferences.edit().putStringSet(tag, values.toSet()).apply()

    override fun setLong(tag: String, value: Long) =
        sharedPreferences.edit().putLong(tag, value).apply()

    override fun setString(tag: String, value: String) =
        sharedPreferences.edit().putString(tag, value).apply()

    override fun clear(tag: String) =
        sharedPreferences.edit().remove(tag).apply()

    override fun clearAll() = sharedPreferences.edit().clear().apply()
}