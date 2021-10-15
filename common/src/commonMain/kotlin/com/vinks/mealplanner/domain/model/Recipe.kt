package com.vinks.mealplanner.domain.model

data class Recipe(
    val id: Long,
    val name: String,
    val ingredients: List<Ingredient>,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)