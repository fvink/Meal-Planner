package com.vinks.mealplanner

import android.app.Application
import android.content.Context
import com.vinks.mealplanner.di.initKoin
import com.vinks.mealplanner.localization.Localization
import com.vinks.mealplanner.presentation.home.homeModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

class MealPlannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Localization.init("en")

        initKoin(
            module { single<Context> { this@MealPlannerApplication } },
            homeModule
        )

        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        }
    }
}