package com.vinks.mealplanner.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vinks.mealplanner.theme.AppTheme
import com.vinks.mealplanner.ui.home.dailystats.DailyStats
import com.vinks.mealplanner.ui.home.dailystats.DailyStatsUiState
import kotlinx.datetime.LocalDate

@ExperimentalMaterialApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val mealPlans by homeViewModel.uiState.collectAsState()
    val mealPlan = mealPlans.firstOrNull()
    val meals = mealPlan?.dailyPlans?.map { it.meals }.orEmpty().flatten()

    val selectedDate = remember { mutableStateOf(LocalDate(2021, 9, 18)) }

    BackdropScaffold(
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        appBar = {
            Box {}
        },
        backLayerContent = {
            HomeContent(previewUiState.copy(selectedDate = selectedDate.value)) {
                selectedDate.value = it
            }
        },
        frontLayerContent = {

        }) {

    }
}

@Composable
fun HomeContent(
    uiState: DailyStatsUiState,
    onDateSelected: (date: LocalDate) -> Unit
) {
    Column(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
            .background(Color(AppTheme.Colors.primaryDark)),
    ) {
        when (uiState) {
            is DailyStatsUiState.Data -> DailyStats(
                state = uiState,
                onDateSelected = onDateSelected
            )
            else -> Text(text = "Loading...")
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(uiState = previewUiState, {})
}

val previewUiState = DailyStatsUiState.Data(
    LocalDate(2021, 9, 18),
    LocalDate(2021, 9, 18),
    2100,
    1500,
    600,
    140,
    100,
    100,
    90,
    90,
    50
)