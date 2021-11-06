package com.vinks.mealplanner.presentation.home

import com.vinks.mealplanner.domain.model.Meal
import com.vinks.mealplanner.presentation.base.ViewState
import kotlinx.datetime.LocalDate

sealed class HomeViewState : ViewState() {

    object Loading : HomeViewState()

    data class Data(
        val currentDate: LocalDate,
        val selectedDate: LocalDate,
        val foodConsumptionData: DailyFoodConsumptionData,
        val meals: List<Meal> = emptyList()
    ) : HomeViewState()
}