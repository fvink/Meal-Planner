package com.vinks.mealplanner.data.local.cache

import com.vinks.mealplanner.domain.model.MealPlan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * In memory cache for simplicity
 */
class MealPlanCache {

    private var mealPlans: List<MealPlan>? = null

    fun getAllMealPlans(): Flow<List<MealPlan>?> = flow {
        emit(mealPlans)
    }

    fun getMealPlanById(id: Long): Flow<MealPlan?> = flow {
        emit(mealPlans?.firstOrNull { it.id == id })
    }

    fun saveMealPlans(mealPlans: List<MealPlan>) {
        this.mealPlans = mealPlans
    }

    fun deleteAllMealPlans() {
        mealPlans = null
    }
}