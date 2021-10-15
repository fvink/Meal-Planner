package com.vinks.mealplanner.domain.repository

import com.vinks.mealplanner.domain.model.MealPlan
import kotlinx.coroutines.flow.Flow

interface MealPlanRepository {
    fun getAllMealPlans(refreshCache: Boolean): Flow<List<MealPlan>>
}