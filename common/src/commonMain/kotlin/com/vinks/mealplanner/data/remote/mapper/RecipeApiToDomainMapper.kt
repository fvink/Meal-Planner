package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.RecipeApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.Recipe

class RecipeApiToDomainMapper(
    private val ingredientApiToDomainMapper: IngredientApiToDomainMapper
) : Mapper<RecipeApiModel, Recipe> {

    override fun map(input: RecipeApiModel): Recipe =
        with(input) {
            Recipe(
                id,
                name,
                ingredientApiToDomainMapper.mapList(ingredients),
                calories,
                protein,
                carbs,
                fat
            )
        }
}