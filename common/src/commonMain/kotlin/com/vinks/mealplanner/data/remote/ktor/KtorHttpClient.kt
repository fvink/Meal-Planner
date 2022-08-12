package com.vinks.mealplanner.data.remote.ktor

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TIMEOUT_MS = 50_000L

val ktorHttpClient = HttpClient {
    defaultRequest {
        host = "get-your-meal.herokuapp.com"
        url {
            protocol = URLProtocol.HTTPS
        }
    }

    install(ContentNegotiation) {
        json(Json {
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

//    install(DataConversion) {
//        convert<DateTime> {
//            val dateFormat = DateFormat("yyyy-MM-ddThh:mm:ssZ")
//
//            decode { values ->
//                values.singleOrNull()?.let { dateFormat.parseUtc(it) }
//            }
//
//            encode { value ->
//                when (value) {
//                    null -> listOf()
//                    is DateTime -> listOf(dateFormat.format(value))
//                    else -> throw DataConversionException("Cannot convert $value as DateTime")
//                }
//            }
//        }
//    }
}