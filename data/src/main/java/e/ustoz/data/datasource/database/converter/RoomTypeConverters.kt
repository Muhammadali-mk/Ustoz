package e.ustoz.data.datasource.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

internal class RoomTypeConverters {

    @TypeConverter
    fun fromBigDecimal(bigDecimal: BigDecimal?): String? =
        bigDecimal?.toString()

    @TypeConverter
    fun toBigDecimal(string: String?): BigDecimal? =
        string?.let { BigDecimal(it) }


    /**
     * Date
     * */
    @TypeConverter
    fun fromDate(date: Date?): String? =
        date?.time?.let { dateFormat.format(it) }

    @TypeConverter
    fun toDate(string: String?): Date? =
        string?.let { dateFormat.parse(it) }

    /**
     * String array
     * */
    @TypeConverter
    fun fromArray(strings: List<String>): String {
        var string = ""
        for (s in strings) string += "$s,"

        return string
    }

    @TypeConverter
    fun toArray(concatenatedStrings: String): List<String> {
        val myStrings: MutableList<String> = mutableListOf()

        for (s in concatenatedStrings.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            myStrings.add(s)

        return myStrings
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val json by lazy { Json.nonstrict }


    @Suppress("SpellCheckingInspection")
    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("uz", "UZ"))
}