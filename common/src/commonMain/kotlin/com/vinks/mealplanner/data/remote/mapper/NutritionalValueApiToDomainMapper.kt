package com.vinks.mealplanner.data.remote.mapper

import com.vinks.mealplanner.data.remote.model.NutritionalValueApiModel
import com.vinks.mealplanner.domain.mapper.Mapper
import com.vinks.mealplanner.domain.model.MeasurementUnit
import com.vinks.mealplanner.domain.model.NutritionalValue
import com.vinks.mealplanner.domain.model.Quantity

class NutritionalValueApiToDomainMapper : Mapper<NutritionalValueApiModel, NutritionalValue> {

    override fun map(input: NutritionalValueApiModel): NutritionalValue =
        with(input) {
            NutritionalValue(
                Quantity(
                    quantityApiModel.amount,
                    MeasurementUnit.forAbbreviation(quantityApiModel.unit)
                ),
                calories,
                protein,
                carbs,
                fat
            )
        }
}