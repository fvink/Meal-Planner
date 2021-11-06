package com.vinks.mealplanner.data.repository

import com.vinks.mealplanner.data.local.cache.MealPlanCache
import com.vinks.mealplanner.data.remote.api.MealPlanApi
import com.vinks.mealplanner.data.remote.mapper.MealPlanApiToDomainMapper
import com.vinks.mealplanner.domain.model.MealPlan
import com.vinks.mealplanner.domain.repository.MealPlanRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapNotNull

class MealPlanDataRepository(
    private val cache: MealPlanCache,
    private val api: MealPlanApi,
    private val apiToDomainMapper: MealPlanApiToDomainMapper,
    private val backgroundDispatcher: CoroutineDispatcher
) : MealPlanRepository {

    override suspend fun getAllMealPlans(refreshCache: Boolean): List<MealPlan> {
        val cachedData = cache.getAllMealPlans().first()
        if (cachedData != null && !refreshCache) {
            return cachedData
        }
        return fetchMealPlans()
    }

    override fun getAllMealPlansFlow(refreshCache: Boolean): Flow<List<MealPlan>> = flow {
        val cachedData = cache.getAllMealPlans().first()
        cachedData?.let { emit(it) }

        if (refreshCache) {
            refreshMealPlans()
        }

        val mealPlans = cache.getAllMealPlans().mapNotNull { it }
        emitAll(mealPlans)
    }.flowOn(backgroundDispatcher)

    private suspend fun refreshMealPlans() {
        try {
            val mealPlans = fetchMealPlans()
            cache.deleteAllMealPlans()
            cache.saveMealPlans(mealPlans)
        } catch (e: Throwable) {
            Napier.e(e.message.orEmpty())
        }
    }

    private suspend fun fetchMealPlans(): List<MealPlan> {
        val mealPlans = api.getAllMealPlans()
        return apiToDomainMapper.mapList(mealPlans)
    }
}