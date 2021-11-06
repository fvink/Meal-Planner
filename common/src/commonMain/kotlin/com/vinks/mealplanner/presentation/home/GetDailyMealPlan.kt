package com.vinks.mealplanner.presentation.home

import com.vinks.mealplanner.domain.model.DailyMealPlan
import com.vinks.mealplanner.domain.repository.MealPlanRepository
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil

class GetDailyMealPlan(
    private val mealPlanRepository: MealPlanRepository
) {
    suspend operator fun invoke(date: LocalDate): DailyMealPlan? {
        val mealPlan = mealPlanRepository.getAllMealPlans().firstOrNull() ?: return null
        val daysSinceMealPlanStartDate = mealPlan.startDate.daysUntil(date)

        return mealPlan.dailyMealPlans[daysSinceMealPlanStartDate % mealPlan.dailyMealPlans.size]
    }
}