package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.MealApiModel
import com.vinks.mealplanner.data.remote.model.MealPlanApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.DailyMealPlan
import com.vinks.mealplanner.domain.model.MealPlan
import kotlinx.datetime.LocalDate

class MealPlanApiToDomainMapper(
    private val mealApiToDomainMapper: MealApiToDomainMapper
) : Mapper<MealPlanApiModel, MealPlan> {

    override fun map(input: MealPlanApiModel): MealPlan =
        with(input) {
            MealPlan(
                id,
                name,
                LocalDate(dateStarted.year, dateStarted.monthNumber, dateStarted.dayOfMonth),
                mapDailyPlans(meals)
            )
        }

    private fun mapDailyPlans(meals: List<List<MealApiModel>>): List<DailyMealPlan> {
        return meals.map {
            DailyMealPlan(mealApiToDomainMapper.mapList(it))
        }
    }
}