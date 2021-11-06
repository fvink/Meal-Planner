package com.vinks.mealplanner.presentation.home

import kotlinx.datetime.LocalDate

data class DailyFoodConsumptionData(
    val date: LocalDate,
    val caloricGoal: Int,
    val consumedCalories: Int,
    val remainingCalories: Int,
    val proteinGoal: Int,
    val consumedProtein: Int,
    val carbsGoal: Int,
    val consumedCarbs: Int,
    val fatGoal: Int,
    val consumedFat: Int
)