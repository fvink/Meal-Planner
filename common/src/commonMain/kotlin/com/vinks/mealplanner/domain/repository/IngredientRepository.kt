package com.vinks.mealplanner.domain.repository

import com.vinks.mealplanner.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    fun getAllIngredients(refreshCache: Boolean = false): Flow<List<Ingredient>>
}