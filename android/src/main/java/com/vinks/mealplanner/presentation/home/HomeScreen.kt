package com.vinks.mealplanner.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinks.mealplanner.localization.localized
import com.vinks.mealplanner.presentation.common.appTypography
import com.vinks.mealplanner.presentation.home.dailystats.DailyStats
import com.vinks.mealplanner.presentation.home.dailystats.DatePicker
import com.vinks.mealplanner.presentation.home.meals.MealsPager
import com.vinks.mealplanner.theme.AppTheme
import kotlinx.datetime.LocalDate

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val state = homeViewModel.state.collectAsState()

    HomeContent(state.value) {
        homeViewModel.onDateSelected(it)
    }
}

@ExperimentalAnimationApi
@Composable
fun HomeContent(
    state: HomeViewState,
    onDateSelected: (date: LocalDate) -> Unit
) {
    when (state) {
        is HomeViewState.Loading -> {

        }
        is HomeViewState.Data -> {
            val isDailyStatsCollapsed = remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(AppTheme.Colors.primaryDark)),
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                DatePicker(
                    currentDate = state.currentDate,
                    selectedDate = state.selectedDate,
                    onDateSelected = onDateSelected
                )
                AnimatedVisibility(visible = !isDailyStatsCollapsed.value) {
                    DailyStats(
                        modifier = Modifier.padding(top = 20.dp),
                        state = state
                    )
                }
                TextButton(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = { isDailyStatsCollapsed.value = !isDailyStatsCollapsed.value },
                ) {
                    Text(
                        text = if (isDailyStatsCollapsed.value) {
                            "Expand daily stats".localized().uppercase()
                        } else {
                            "Collapse daily stats".localized().uppercase()
                        },
                        style = appTypography.caption.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color(AppTheme.Colors.textLighterGray)
                        )
                    )
                }
                MealsPager(
                    modifier = Modifier.weight(1f),
                    meals = state.meals
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(state = homeViewStatePreview, {})
}

val previewFoodConsumptionData = DailyFoodConsumptionData(
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

val homeViewStatePreview = HomeViewState.Data(
    LocalDate(2021, 9, 18),
    LocalDate(2021, 9, 18),
    foodConsumptionData = previewFoodConsumptionData,
    emptyList()
)