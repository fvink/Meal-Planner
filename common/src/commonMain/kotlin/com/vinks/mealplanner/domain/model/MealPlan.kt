package com.vinks.mealplanner.domain.model

import kotlinx.datetime.LocalDate

data class MealPlan(
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val dailyMealPlans: List<DailyMealPlan>
)

data class DailyMealPlan(
    val meals: List<Meal>
)