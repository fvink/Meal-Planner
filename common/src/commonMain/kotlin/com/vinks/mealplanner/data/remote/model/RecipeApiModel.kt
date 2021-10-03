package com.vinks.mealplanner.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeApiModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("calories") val calories: Double,
    @SerialName("protein") val protein: Double,
    @SerialName("carbs") val carbs: Double,
    @SerialName("fat") val fat: Double,
    @SerialName("steps") val steps: String,
    @SerialName("ingredients") val ingredients: List<IngredientApiModel>
)