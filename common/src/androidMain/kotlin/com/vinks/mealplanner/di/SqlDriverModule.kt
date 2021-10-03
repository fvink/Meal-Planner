package com.vinks.mealplanner.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.vinks.mealplanner.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val sqlDriverModule = module {
    single<SqlDriver> { AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "test.db") }
}