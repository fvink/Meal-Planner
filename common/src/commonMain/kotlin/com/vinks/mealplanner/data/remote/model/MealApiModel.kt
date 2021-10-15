package com.vinks.mealplanner.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealApiModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("recipes") val recipes: List<RecipeApiModel>
)