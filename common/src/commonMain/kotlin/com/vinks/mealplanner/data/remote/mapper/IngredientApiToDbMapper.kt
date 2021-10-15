package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.IngredientApiModel
import com.vinks.mealplanner.database.IngredientDbModel
import com.vinks.mealplanner.domain.mapper.Mapper

class IngredientApiToDbMapper : Mapper<IngredientApiModel, IngredientDbModel> {

    override fun map(input: IngredientApiModel): IngredientDbModel =
        with(input) {
            IngredientDbModel(
                id,
                name,
                nutritionalValueApiModel.quantityApiModel.amount,
                nutritionalValueApiModel.quantityApiModel.unit,
                nutritionalValueApiModel.calories,
                nutritionalValueApiModel.protein,
                nutritionalValueApiModel.carbs,
                nutritionalValueApiModel.fat
            )
        }
}