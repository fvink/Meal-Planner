package com.vinks.mealplanner.presentation.home.dailystats

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinks.mealplanner.localization.localized
import com.vinks.mealplanner.presentation.common.appTypography
import com.vinks.mealplanner.presentation.home.HomeViewState
import com.vinks.mealplanner.presentation.home.homeViewStatePreview
import com.vinks.mealplanner.presentation.util.screenWidth
import com.vinks.mealplanner.theme.AppTheme
import kotlinx.datetime.LocalDate

@Composable
fun DatePicker(
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (date: LocalDate) -> Unit
) {
    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableStateOf(selectedDate.dayOfMonth - 1) }

    val daysInCurrentMonth = currentDate.month.length(false)

    val screenWidth = LocalContext.current.screenWidth()

    Column {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = selectedDate.month.name,
            style = appTypography.body2.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(AppTheme.Colors.white)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(start = screenWidth / 2 - 20.dp, end = screenWidth / 2 - 20.dp)
        ) {
            items(daysInCurrentMonth) { index ->
                DatePickerItem(
                    dayOfMonth = index + 1,
                    isCurrentDate = currentDate.dayOfMonth == index + 1,
                    isSelected = index == selectedIndex,
                    onDaySelected = { dayOfMonth ->
                        selectedIndex = index
                        onDateSelected(
                            with(currentDate) {
                                LocalDate(year, month, dayOfMonth)
                            }
                        )
                    }
                )

                LaunchedEffect(selectedIndex) {
                    listState.animateScrollToItem(selectedIndex)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = AppTheme.Colors.primaryDark
)
@Composable
fun DatePickerPreview() {
    DatePicker(
        currentDate = LocalDate(2021, 10, 21),
        selectedDate = LocalDate(2021, 10, 23),
        onDateSelected = {})
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

@Composable
fun DailyStats(
    modifier: Modifier,
    state: HomeViewState.Data
) {
    val data = state.foodConsumptionData
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
                    text = data.remainingCalories.toString(),
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
                            text = data.caloricGoal.toString(),
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
                            text = data.consumedCalories.toString(),
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
            MacroProgressBar(
                modifier = Modifier.padding(bottom = 20.dp),
                label = "Protein".localized(),
                consumed = data.consumedProtein,
                goal = data.proteinGoal
            )
            MacroProgressBar(
                modifier = Modifier.padding(bottom = 20.dp),
                label = "Carbs".localized(),
                consumed = data.consumedCarbs,
                goal = data.carbsGoal
            )
            MacroProgressBar(
                modifier = Modifier,
                label = "Fat".localized(),
                consumed = data.consumedFat,
                goal = data.fatGoal
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = AppTheme.Colors.primaryDark
)
@Composable
fun DateStatsPreview() {
    DailyStats(modifier = Modifier, state = homeViewStatePreview)
}

@Composable
fun MacroProgressBar(
    modifier: Modifier,
    label: String,
    consumed: Int,
    goal: Int
) {
    Column(modifier = modifier) {
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