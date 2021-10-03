package com.vinks.mealplanner.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuantityApiModel(
    @SerialName("amount") val amount: Double,
    @SerialName("unit") val unit: String
)