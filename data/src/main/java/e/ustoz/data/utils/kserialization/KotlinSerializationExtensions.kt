package e.ustoz.data.utils.kserialization

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Suppress("EXPERIMENTAL_API_USAGE")
internal fun <T> SerializationStrategy<T>.asJsonElement(format: Json = Json.indented, obj: T) =
    with(Json) { format.parseJson(stringify(this@asJsonElement, obj)) }

@Suppress("EXPERIMENTAL_API_USAGE")
internal fun <T> SerializationStrategy<T>.asJsonElement(obj: T) =
    asJsonElement(Json.indented, obj)

internal fun JsonObject.Companion.create(vararg pair: Pair<String, JsonElement?>): JsonObject =
    create(pair.toMap())

internal fun JsonObject.Companion.create(content: Map<String, JsonElement?>): JsonObject {
    val result: MutableMap<String, JsonElement> = hashMapOf()

    for (entry: Map.Entry<String, JsonElement?> in content) {
        if (entry.value == null) continue
        result[entry.key] = checkNotNull(entry.value)
    }

    return JsonObject(result)
}