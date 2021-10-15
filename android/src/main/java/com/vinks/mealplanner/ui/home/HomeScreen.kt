package com.vinks.mealplanner.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.model.Meal
import com.vinks.mealplanner.domain.model.MealPlan
import com.vinks.mealplanner.theme.AppTheme
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.launchIn
import java.time.LocalDate

@ExperimentalMaterialApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val mealPlans by homeViewModel.uiState.collectAsState()
    val mealPlan = mealPlans.firstOrNull()
    val meals = mealPlan?.dailyPlans?.map { it.meals }.orEmpty().flatten()

    BackdropScaffold(
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        appBar = {
            Box {}
        },
        backLayerContent = {
            DailyCaloricStatsContent(meals)
        },
        frontLayerContent = {

        }) {

    }
}

@Composable
fun DailyCaloricStatsContent(meals: List<Meal>) {
    Napier.d("SHOWING INGREDS")
    LazyColumn(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
            .background(Color(AppTheme.Colors.primaryDark)),
    ) {

        items(meals.size) { index ->
            Text(text = meals[index].name)
        }
    }
}

@Composable
fun DatePicker(dates: List<LocalDate>) {
    LazyRow {
        items(dates.size) { index ->
            Text(
                text = dates[index].dayOfMonth.toString(),
                color = Color(AppTheme.Colors.textGray),
            )
        }
    }
}