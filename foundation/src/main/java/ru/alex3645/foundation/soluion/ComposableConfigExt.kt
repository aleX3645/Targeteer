package ru.alex3645.foundation.soluion

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass


@PublishedApi
internal inline val <reified Config : Parcelable> KClass<Config>.navType: NavType<Config>
    get() = object : NavType<Config>(isNullableAllowed = false) {
        override fun put(bundle: Bundle, key: String, value: Config) =
            bundle.putParcelable(key, value)

        override fun get(bundle: Bundle, key: String): Config? =
            bundle.getParcelable(key)

        override fun parseValue(value: String): Config =
            Json.decodeFromString<Config>(value)
    }