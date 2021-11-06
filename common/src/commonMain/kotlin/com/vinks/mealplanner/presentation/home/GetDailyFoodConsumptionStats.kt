package com.vinks.mealplanner.presentation.home

import kotlinx.datetime.LocalDate

class GetDailyFoodConsumptionStats(

) {
    operator fun invoke(date: LocalDate): DailyFoodConsumptionData {
        return DailyFoodConsumptionData(
            date = date,
            caloricGoal = 3000,
            consumedCalories = 2560,
            remainingCalories = 440,
            proteinGoal = 140,
            consumedProtein = 100,
            carbsGoal = 300,
            consumedCarbs = 260,
            fatGoal = 80,
            consumedFat = 70
        )
    }
}