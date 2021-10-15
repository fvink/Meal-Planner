package com.vinks.mealplanner.di

import com.vinks.mealplanner.data.local.cache.IngredientCache
import com.vinks.mealplanner.data.local.cache.MealPlanCache
import com.vinks.mealplanner.data.local.mapper.IngredientDbToDomainMapper
import com.vinks.mealplanner.data.remote.api.IngredientApi
import com.vinks.mealplanner.data.remote.api.MealPlanApi
import com.vinks.mealplanner.data.remote.ktor.ktorHttpClient
import com.vinks.mealplanner.data.remote.mapper.IngredientApiToDbMapper
import com.vinks.mealplanner.data.remote.mapper.IngredientApiToDomainMapper
import com.vinks.mealplanner.data.remote.mapper.MealApiToDomainMapper
import com.vinks.mealplanner.data.remote.mapper.MealPlanApiToDomainMapper
import com.vinks.mealplanner.data.remote.mapper.NutritionalValueApiToDomainMapper
import com.vinks.mealplanner.data.remote.mapper.RecipeApiToDomainMapper
import com.vinks.mealplanner.data.repository.IngredientDataRepository
import com.vinks.mealplanner.data.repository.MealPlanDataRepository
import com.vinks.mealplanner.db.AppDatabase
import com.vinks.mealplanner.domain.repository.IngredientRepository
import com.vinks.mealplanner.domain.repository.MealPlanRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single { ktorHttpClient }
    single { AppDatabase(get()) }
    factory { IngredientCache(get(), get(named(backgroundDispatcher))) }
    factory { IngredientApi(get()) }
    factory<IngredientRepository> { IngredientDataRepository(get(), get(), get(), get(), get(named(backgroundDispatcher))) }

    factory { MealPlanCache() }
    factory { MealPlanApi(get()) }
    factory<MealPlanRepository> { MealPlanDataRepository(get(), get(), get(), get(named(backgroundDispatcher))) }

    factory { IngredientDbToDomainMapper() }
    factory { IngredientApiToDbMapper() }
    factory { IngredientApiToDomainMapper(get()) }
    factory { MealApiToDomainMapper(get()) }
    factory { MealPlanApiToDomainMapper(get()) }
    factory { NutritionalValueApiToDomainMapper() }
    factory { RecipeApiToDomainMapper(get()) }
}