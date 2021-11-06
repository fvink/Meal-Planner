package com.vinks.mealplanner.presentation.util

import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.model.Meal
import com.vinks.mealplanner.domain.model.MeasurementUnit
import com.vinks.mealplanner.domain.model.NutritionalValue
import com.vinks.mealplanner.domain.model.Quantity
import com.vinks.mealplanner.domain.model.Recipe

object PreviewData {
    val meals = listOf(
        Meal(
            3, "Day 1 Lunch", listOf(
                Recipe(
                    9, "Lentil Tortillas", calories = 869.03, protein = 39.66, carbs = 148.09, fat = 11.62, ingredients = listOf(
                        Ingredient(1, "onion", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 20.0, 0.5, 4.5, 0.0)),
                        Ingredient(2, "carrot", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 16.4, 0.0, 3.6, 0.0)),
                        Ingredient(3, "green peas", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 16.4, 0.0, 3.6, 0.0)),
                        Ingredient(4, "lentils", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 16.4, 0.0, 3.6, 0.0)),
                        Ingredient(6, "cumin", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 16.4, 0.0, 3.6, 0.0)),
                    )
                )
            )
        ),
        Meal(
            4, "Day 1 Dinner", listOf(
                Recipe(
                    10, "Chickpea Buddha Bowl", calories = 808.22, protein = 39.12, carbs = 104.09, fat = 25.62, ingredients = listOf(
                        Ingredient(1, "onion", NutritionalValue(Quantity(50.0, MeasurementUnit.Gram), 20.0, 0.5, 4.5, 0.0)),
                        Ingredient(13, "sweet potato", NutritionalValue(Quantity(100.0, MeasurementUnit.Gram), 86.0, 1.6, 20.0, 0.1)),
                        Ingredient(14, "broccoli", NutritionalValue(Quantity(60.0, MeasurementUnit.Gram), 22.8, 1.98, 1.38, 0.6)),
                        Ingredient(15, "spinach", NutritionalValue(Quantity(100.0, MeasurementUnit.Gram), 25.0, 3.2, 0.6, 0.0)),
                        Ingredient(
                            16,
                            "chickpeas (cooked)",
                            NutritionalValue(Quantity(260.0, MeasurementUnit.Gram), 423.4, 23.0, 71.6, 6.7)
                        ),
                    )
                )
            )
        )
    )
}