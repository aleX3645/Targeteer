package ru.alex3645.foundation.soluion

import android.os.Parcelable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

public interface ComposableSolutionRoute {
    public val id: Id
    public val config: Config?

    @JvmInline
    public value class Id(
        public val value: String
    )

    @JvmInline
    public value class Config(
        public val json: String
    ) {
        public companion object {
            public inline fun <reified T : Parcelable> create(config: T): Config {
                val jsonClient = Json {
                    serializersModule = SerializersModule {
                        polymorphic(Config::class)
                        ignoreUnknownKeys = true
                    }
                }
                return Config(jsonClient.encodeToString(config))
            }
        }
    }
}