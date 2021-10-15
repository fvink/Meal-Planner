package com.vinks.mealplanner.domain.model

import kotlinx.datetime.LocalDateTime

data class MealPlan(
    val id: Long,
    val name: String,
    val startDate: LocalDateTime,
    val dailyPlans: List<DailyPlan>
)

data class DailyPlan(
    val meals: List<Meal>
)