package com.vinks.mealplanner.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionalValueApiModel(
    @SerialName("quantity") val quantityApiModel: QuantityApiModel,
    @SerialName("calories") val calories: Double,
    @SerialName("protein") val protein: Double,
    @SerialName("carbs") val carbs: Double,
    @SerialName("fat") val fat: Double
)