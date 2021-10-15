package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.MealApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.Meal

class MealApiToDomainMapper(
    private val recipeApiToDomainMapper: RecipeApiToDomainMapper
) : Mapper<MealApiModel, Meal> {

    override fun map(input: MealApiModel): Meal =
        with(input) {
            Meal(
                id,
                name,
                recipeApiToDomainMapper.mapList(recipes)
            )
        }
}