package com.vinks.mealplanner.domain.model

data class Meal(
    val id: Long,
    val name: String,
    val recipes: List<Recipe>
)

val Meal.totalCalories
    get() = recipes.sumOf { it.calories }

val Meal.totalProtein
    get() = recipes.sumOf { it.protein }

val Meal.totalCarbs
    get() = recipes.sumOf { it.carbs }

val Meal.totalFat
    get() = recipes.sumOf { it.fat }