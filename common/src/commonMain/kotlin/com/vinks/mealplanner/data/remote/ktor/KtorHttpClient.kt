package com.vinks.mealplanner.data.remote.ktor

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.host
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

private const val TIMEOUT_MS = 50_000L

val ktorHttpClient = HttpClient {
    defaultRequest {
        host = "get-your-meal.herokuapp.com"
        url {
            protocol = URLProtocol.HTTPS
        }
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(HttpTimeout) {
        requestTimeoutMillis = TIMEOUT_MS
        connectTimeoutMillis = TIMEOUT_MS
        socketTimeoutMillis = TIMEOUT_MS
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.d(tag = "http", message = message)
            }
        }
        level = LogLevel.BODY
    }
}