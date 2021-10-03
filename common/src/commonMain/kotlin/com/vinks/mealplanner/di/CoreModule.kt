package com.vinks.mealplanner.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val backgroundDispatcher = "backgroundDispatcher"

val coreModule = module {
    single(named(backgroundDispatcher)) { Dispatchers.Default }
}