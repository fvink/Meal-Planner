package com.vinks.mealplanner.domain.repository

import com.vinks.mealplanner.domain.model.MealPlan
import kotlinx.coroutines.flow.Flow

interface MealPlanRepository {
    suspend fun getAllMealPlans(refreshCache: Boolean = false): List<MealPlan>
    fun getAllMealPlansFlow(refreshCache: Boolean = false): Flow<List<MealPlan>>
}