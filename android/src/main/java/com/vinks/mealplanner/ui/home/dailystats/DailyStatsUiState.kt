package com.vinks.mealplanner.ui.home.dailystats

import kotlinx.datetime.LocalDate

sealed class DailyStatsUiState {

    object Loading : DailyStatsUiState()

    object Error : DailyStatsUiState()

    data class Data(
        val currentDate: LocalDate,
        val selectedDate: LocalDate,
        val caloricGoal: Int,
        val consumedCalories: Int,
        val remainingCalories: Int,
        val proteinGoal: Int,
        val consumedProtein: Int,
        val carbsGoal: Int,
        val consumedCarbs: Int,
        val fatGoal: Int,
        val consumedFat: Int
    ) : DailyStatsUiState()
}