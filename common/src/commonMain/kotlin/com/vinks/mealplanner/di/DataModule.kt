package com.vinks.mealplanner.di

import com.vinks.mealplanner.data.local.IngredientCache
import com.vinks.mealplanner.data.local.mapper.IngredientDbToDomainMapper
import com.vinks.mealplanner.data.remote.api.IngredientApi
import com.vinks.mealplanner.data.remote.ktor.ktorHttpClient
import com.vinks.mealplanner.data.remote.mapper.IngredientApiToDbMapper
import com.vinks.mealplanner.data.repository.IngredientDataRepository
import com.vinks.mealplanner.db.AppDatabase
import com.vinks.mealplanner.domain.repository.IngredientRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single { ktorHttpClient }
    single { AppDatabase(get()) }
    factory { IngredientCache(get(), get(named(backgroundDispatcher))) }
    factory { IngredientApi(get()) }
    factory<IngredientRepository> { IngredientDataRepository(get(), get(), get(), get(), get(named(backgroundDispatcher))) }
    factory { IngredientDbToDomainMapper() }
    factory { IngredientApiToDbMapper() }
}