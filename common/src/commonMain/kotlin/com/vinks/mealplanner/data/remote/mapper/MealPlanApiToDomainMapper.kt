package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.MealApiModel
import com.vinks.mealplanner.data.remote.model.MealPlanApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.DailyPlan
import com.vinks.mealplanner.domain.model.MealPlan

class MealPlanApiToDomainMapper(
    private val mealApiToDomainMapper: MealApiToDomainMapper
) : Mapper<MealPlanApiModel, MealPlan> {

    override fun map(input: MealPlanApiModel): MealPlan =
        with(input) {
            MealPlan(
                id,
                name,
                dateStarted,
                mapDailyPlans(meals)
            )
        }

    private fun mapDailyPlans(meals: List<List<MealApiModel>>): List<DailyPlan> {
        return meals.map {
            DailyPlan(mealApiToDomainMapper.mapList(it))
        }
    }
}