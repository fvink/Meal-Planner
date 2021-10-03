package com.vinks.mealplanner.data.remote.api

import com.vinks.mealplanner.data.remote.model.IngredientApiModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class IngredientApi(private val client: HttpClient) {

    suspend fun getAllIngredients(): List<IngredientApiModel> = client.get("ingredients")
}