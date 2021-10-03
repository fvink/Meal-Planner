package com.vinks.mealplanner.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(vararg modules: Module) {
    startKoin {
        modules(
            *modules,
            coreModule,
            sqlDriverModule,
            dataModule
        )
    }
}

expect val sqlDriverModule: Module