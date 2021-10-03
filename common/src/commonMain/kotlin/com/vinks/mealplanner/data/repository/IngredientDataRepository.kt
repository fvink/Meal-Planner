package com.vinks.mealplanner.data.repository

import com.vinks.mealplanner.data.local.IngredientCache
import com.vinks.mealplanner.data.local.mapper.IngredientDbToDomainMapper
import com.vinks.mealplanner.data.remote.api.IngredientApi
import com.vinks.mealplanner.data.remote.mapper.IngredientApiToDbMapper
import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.repository.IngredientRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class IngredientDataRepository(
    private val cache: IngredientCache,
    private val api: IngredientApi,
    private val dbToDomainMapper: IngredientDbToDomainMapper,
    private val apiToDbMapper: IngredientApiToDbMapper,
    private val backgroundDispatcher: CoroutineDispatcher
) : IngredientRepository {

    override fun getAllIngredients(refreshCache: Boolean): Flow<List<Ingredient>> = flow {
        val cachedData = cache.getAllIngredients().first().map { dbToDomainMapper.map(it) }
        emit(cachedData)

        if (refreshCache) {
            refreshIngredients()
        }

        val ingredients = cache.getAllIngredients().map { ingredients -> ingredients.map { dbToDomainMapper.map(it) } }
        emitAll(ingredients)
    }.flowOn(backgroundDispatcher)

    private suspend fun refreshIngredients() {
        val ingredients = api.getAllIngredients()
        cache.deleteAllIngredients()
        cache.saveIngredients(apiToDbMapper.mapList(ingredients))
    }
}