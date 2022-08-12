package com.vinks.mealplanner.data.remote.api

import com.vinks.mealplanner.data.remote.model.MealPlanApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MealPlanApi(private val client: HttpClient) {

    suspend fun getAllMealPlans(): List<MealPlanApiModel> = client.get("meal-plans").body()
}