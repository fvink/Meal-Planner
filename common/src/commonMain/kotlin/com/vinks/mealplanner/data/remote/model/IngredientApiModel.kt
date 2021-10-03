package com.vinks.mealplanner.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientApiModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("nutritional_value") val nutritionalValueApiModel: NutritionalValueApiModel
)