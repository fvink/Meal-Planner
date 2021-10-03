package com.vinks.mealplanner.domain.model

data class NutritionalValue(
    val quantity: Quantity,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)

fun NutritionalValue.forQuantity(quantity: Quantity): NutritionalValue {
    val ratio = quantity.amount / this.quantity.amount

    return NutritionalValue(
        quantity,
        ratio * calories,
        protein * ratio,
        carbs * ratio,
        fat * ratio
    )
}