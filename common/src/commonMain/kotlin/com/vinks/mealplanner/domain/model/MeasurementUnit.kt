package com.vinks.mealplanner.domain.model

enum class MeasurementUnit(val abbreviation: String) {
    Gram("g"),
    Kilogram("kg"),
    Milliliter("ml"),
    Liter("l"),
    Pound("lb"),
    Ounce("Oz"),
    FluidOunce("fl. Oz"),
    Tablespoon("Tbsp"),
    Teaspoon("tsp"),
    Cup("c"),
    Quart("qt"),
    Pint("pt"),
    Gallon("gal");

    companion object {
        fun forAbbreviation(abbreviation: String) = values().first { it.abbreviation == abbreviation }
    }

}