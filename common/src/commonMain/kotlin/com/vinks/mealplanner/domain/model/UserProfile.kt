package com.vinks.mealplanner.domain.model

data class UserProfile(
    val id: Long,
    val name: String,
    val nutritionGoals: NutritionGoals
)