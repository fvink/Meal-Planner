package com.vinks.mealplanner.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.vinks.mealplanner.data.local.sqldelight.transactionWithContext
import com.vinks.mealplanner.database.IngredientDbModel
import com.vinks.mealplanner.db.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class IngredientCache(
    private val database: AppDatabase,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    fun getAllIngredients(): Flow<List<IngredientDbModel>> {
        return database.ingredientDaoQueries
            .selectAll()
            .asFlow()
            .mapToList(backgroundDispatcher)
    }

    suspend fun saveIngredients(ingredients: List<IngredientDbModel>) {
        database.transactionWithContext(backgroundDispatcher) {
            ingredients.forEach { ingredient ->
                with(ingredient) {
                    database.ingredientDaoQueries.insertIngredient(
                        id, name, amount, unit, calories, protein, carbs, fat
                    )
                }
            }
        }
    }

    suspend fun deleteAllIngredients() {
        database.transactionWithContext(backgroundDispatcher) {
            database.ingredientDaoQueries.deleteAll()
        }
    }
}