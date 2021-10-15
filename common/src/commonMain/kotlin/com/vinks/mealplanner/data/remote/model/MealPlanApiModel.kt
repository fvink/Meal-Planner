package com.vinks.mealplanner.data.remote.model

import com.vinks.mealplanner.data.serializers.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class MealPlanApiModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("date_started") @Serializable(LocalDateTimeSerializer::class) val dateStarted: LocalDateTime,
    @SerialName("meals") val meals: List<List<MealApiModel>>
)