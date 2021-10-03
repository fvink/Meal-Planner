package com.vinks.mealplanner.domain.model

import java.time.LocalDate

data class MealPlan(
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val dailyPlans: List<DailyPlan>
)

data class DailyPlan(
    val daysFromMealPlanStartDate: Int,
    val recipes: List<Recipe>
)