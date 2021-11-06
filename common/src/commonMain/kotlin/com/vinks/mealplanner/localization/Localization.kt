package com.vinks.mealplanner.localization

import io.github.aakira.napier.Napier

object Localization {
    internal var translations = emptyMap<String, String>()
        private set

    fun init(languageCode: String) {
        translations = when (languageCode) {
            "en" -> getEnTranslations()
            else -> throw IllegalArgumentException("No translations found for $languageCode")
        }
    }
}

fun String.localized(): String {
    return Localization.translations[this.lowercase()] ?: run {
        Napier.e("No translation found for $this")
        this
    }
}