package com.vinks.mealplanner.presentation.home

import com.vinks.mealplanner.presentation.base.BaseViewModel
import com.vinks.mealplanner.util.currentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    private val getDailyMealPlan: GetDailyMealPlan,
    private val getDailyFoodConsumptionStats: GetDailyFoodConsumptionStats
) : BaseViewModel() {

    private val _state = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    override val state: StateFlow<HomeViewState> = _state.stateIn(
        scope = vmScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeViewState.Loading,
    )

    init {
        onDateSelected(currentDate)
    }

    fun onDateSelected(date: LocalDate) {
        vmScope.launch {
            val foodConsumptionStats = getDailyFoodConsumptionStats(date)
            val dailyMealPlan = getDailyMealPlan(date)

            _state.value = HomeViewState.Data(
                currentDate = currentDate,
                selectedDate = date,
                foodConsumptionData = foodConsumptionStats,
                meals = dailyMealPlan?.meals.orEmpty()
            )
        }
    }

}