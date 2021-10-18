package com.vinks.mealplanner.localization

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
    return Localization.translations[this.lowercase()] ?: throw IllegalArgumentException("No translation found for $this")
}