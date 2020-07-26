package e.ustoz.uz.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Suppress("EXPERIMENTAL_API_USAGE")
val Json.Default.actual: Json
    get() = lazyJson

private val lazyJson: Json by lazy {
    Json(
        JsonConfiguration.Stable.copy(
            isLenient = true,
            ignoreUnknownKeys = true,
            prettyPrint = true,
            serializeSpecialFloatingPointValues = true,
            useArrayPolymorphism = true
        )
    )
}