package com.vinks.mealplanner.di

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vinks.mealplanner.db.AppDatabase
import org.koin.dsl.module

actual val sqlDriverModule = module {
    single { NativeSqliteDriver(AppDatabase.Schema, "test.db") }
}