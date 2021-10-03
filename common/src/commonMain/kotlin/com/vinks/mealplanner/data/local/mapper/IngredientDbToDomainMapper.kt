package com.vinks.mealplanner.data.local.mapper

import com.vinks.mealplanner.database.IngredientDbModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.model.MeasurementUnit
import com.vinks.mealplanner.domain.model.NutritionalValue
import com.vinks.mealplanner.domain.model.Quantity

class IngredientDbToDomainMapper : Mapper<IngredientDbModel, Ingredient> {

    override fun map(input: IngredientDbModel): Ingredient =
        Ingredient(
            input.id,
            input.name,
            NutritionalValue(
                Quantity(
                    input.amount,
                    MeasurementUnit.forAbbreviation(input.unit)
                ),
                input.calories,
                input.protein,
                input.carbs,
                input.fat
            )
        )
}