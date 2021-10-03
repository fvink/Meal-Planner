package com.vinks.mealplanner.domain.model

data class Ingredient(
    val id: Long,
    val name: String,
    val nutritionalValue: NutritionalValue
)

fun Ingredient.forQuantity(quantity: Quantity): NutritionalValue =
    nutritionalValue.forQuantity(quantity)