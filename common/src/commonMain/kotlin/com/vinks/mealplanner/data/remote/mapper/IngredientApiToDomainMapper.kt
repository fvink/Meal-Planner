package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.IngredientApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.Ingredient

class IngredientApiToDomainMapper(
    private val nutritionalValueApiToDomainMapper: NutritionalValueApiToDomainMapper
) : Mapper<IngredientApiModel, Ingredient> {

    override fun map(input: IngredientApiModel): Ingredient =
        with(input) {
            Ingredient(
                id,
                name,
                nutritionalValueApiToDomainMapper.map(nutritionalValueApiModel)
            )
        }
}