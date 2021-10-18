package com.vinks.mealplanner.ui.home.dailystats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinks.mealplanner.localization.localized
import com.vinks.mealplanner.theme.AppTheme
import com.vinks.mealplanner.ui.common.appTypography
import com.vinks.mealplanner.ui.home.previewUiState
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@Composable
fun DailyStats(
    state: DailyStatsUiState.Data,
    onDateSelected: (date: LocalDate) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        DatePicker(
            currentDate = state.currentDate,
            selectedDate = state.selectedDate,
            onDateSelected = onDateSelected
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        CalorieStats(state = state)
    }
}

@Preview(
    showBackground = true,
    backgroundColor = AppTheme.Colors.primaryDark
)
@Composable
fun DateStatsPreview() {
    DailyStats(state = previewUiState, {})
}

@Composable
fun DatePicker(
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (date: LocalDate) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val daysInCurrentMonth = currentDate.month.length(false)

    val context = LocalContext.current.resources
    val displayMetrics = context.displayMetrics
    val screenWidth = (displayMetrics.widthPixels / displayMetrics.density).dp

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(start = screenWidth / 2 - 20.dp, end = screenWidth / 2 - 20.dp)
    ) {
        items(daysInCurrentMonth) { index ->
            DatePickerItem(
                dayOfMonth = index + 1,
                isCurrentDate = currentDate.dayOfMonth == index + 1,
                isSelected = selectedDate.dayOfMonth == index + 1,
                onDaySelected = { dayOfMonth ->
                    onDateSelected(
                        with(currentDate) {
                            LocalDate(year, month, dayOfMonth)
                        }
                    )
                }
            )
        }

        coroutineScope.launch {
            listState.animateScrollToItem(selectedDate.dayOfMonth - 1)
        }
    }
}

@Composable
fun DatePickerItem(
    dayOfMonth: Int,
    isCurrentDate: Boolean,
    isSelected: Boolean,
    onDaySelected: (day: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isSelected) Color(AppTheme.Colors.white) else Color.Transparent,
                shape = CircleShape.copy()
            )
            .clickable { onDaySelected(dayOfMonth) }
    ) {
        Text(
            text = dayOfMonth.toString(),
            style = appTypography.body1,
            color = when {
                isSelected -> Color.Black
                isCurrentDate -> Color.White
                else -> Color(AppTheme.Colors.textGray)
            },
            modifier = Modifier
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(3.dp)
                .align(Alignment.Center)
                .offset(y = 13.dp)
                .background(
                    color = when {
                        isSelected && isCurrentDate -> Color.Black
                        isCurrentDate -> Color.White
                        else -> Color.Transparent
                    },
                    shape = CircleShape.copy()
                )
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = AppTheme.Colors.primaryDark
)
@Composable
fun CalorieStatsPreview() {
    CalorieStats(state = previewUiState)
}

@Composable
fun CalorieStats(state: DailyStatsUiState) {
    state as DailyStatsUiState.Data

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .height(IntrinsicSize.Min)
                .padding(end = 30.dp)
        ) {
            Column {
                Text(
                    text = "Remaining".localized(),
                    style = appTypography.body2,
                    color = Color(AppTheme.Colors.textGray)
                )
                Text(
                    text = state.remainingCalories.toString(),
                    style = appTypography.h4.copy(fontSize = 38.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Column {
                        Text(
                            text = "Goal".localized(),
                            style = appTypography.body2,
                            color = Color(AppTheme.Colors.textGray)
                        )
                        Text(
                            text = state.caloricGoal.toString(),
                            style = appTypography.h5,
                            color = Color(AppTheme.Colors.textLighterGray)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp)
                    ) {
                        Text(
                            text = "Consumed".localized(),
                            style = appTypography.body2,
                            color = Color(AppTheme.Colors.textGray)
                        )
                        Text(
                            text = state.consumedCalories.toString(),
                            style = appTypography.h5,
                            color = Color(AppTheme.Colors.textLighterGray)
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5.dp)
                    .align(Alignment.TopEnd),
                color = Color(AppTheme.Colors.white).copy(alpha = 0.05f),
            )
        }
        Column(
            modifier = Modifier
                .weight(0.4f)
        ) {
            MacroProgressBar(label = "Protein".localized(), consumed = state.consumedProtein, goal = state.proteinGoal)
            MacroProgressBar(label = "Carbs".localized(), consumed = state.consumedCarbs, goal = state.carbsGoal)
            MacroProgressBar(label = "Fat".localized(), consumed = state.consumedFat, goal = state.fatGoal)
        }
    }
}

@Composable
fun MacroProgressBar(label: String, consumed: Int, goal: Int) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = appTypography.body2,
                color = Color(AppTheme.Colors.textGray)
            )
            Text(
                text = "$consumed/$goal",
                style = appTypography.body2,
                color = Color(AppTheme.Colors.textGray),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    bottom = 20.dp,
                    start = 3.dp,
                    end = 2.dp
                )
        ) {
            val canvasWidth = size.width

            drawLine(
                start = Offset.Zero,
                end = Offset(x = canvasWidth, y = 0f),
                color = Color(AppTheme.Colors.progressBarEmpty),
                strokeWidth = 10f,
                cap = StrokeCap.Round
            )

            drawLine(
                start = Offset.Zero,
                end = Offset(x = canvasWidth * consumed / goal, y = 0f),
                color = Color(AppTheme.Colors.white),
                strokeWidth = 10f,
                cap = StrokeCap.Round
            )
        }
    }
}