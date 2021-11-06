package com.vinks.mealplanner.presentation.home.meals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.vinks.mealplanner.domain.model.Meal
import com.vinks.mealplanner.domain.model.Recipe
import com.vinks.mealplanner.presentation.common.appTypography
import com.vinks.mealplanner.presentation.util.PreviewData
import com.vinks.mealplanner.theme.AppTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.exp

@ExperimentalAnimationApi
@Preview
@Composable
fun MealsPagerPreview() {
    MealsPager(
        modifier = Modifier,
        meals = PreviewData.meals
    )
}

@Composable
fun MealTabs(
    meals: List<Meal>,
    selectedTabIndex: Int,
    onMealIndexSelected: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(meals.size) { index ->
            val meal = meals[index]

            val textColor = if (index == selectedTabIndex) {
                Color(AppTheme.Colors.white)
            } else {
                Color(AppTheme.Colors.textGray)
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .clickable {
                        onMealIndexSelected(index)
                    },
                text = meal.name,
                style = appTypography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )
        }

        coroutineScope.launch {
            listState.animateScrollToItem(selectedTabIndex)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun MealsPager(
    modifier: Modifier,
    meals: List<Meal>
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        MealTabs(
            meals = meals,
            selectedTabIndex = pagerState.currentPage
        ) { selectedIndex ->
            coroutineScope.launch {
                pagerState.animateScrollToPage(selectedIndex)
            }
        }
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            count = meals.size,
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) { page ->
            Box(
                Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.97f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    )
            ) {
                val meal = meals[page]
                MealPage(meal = meal)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MealPage(meal: Meal) {
    val expandedRecipeIndex = remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(meal.recipes.size) { index ->
            val recipe = meal.recipes[index]
            RecipeOverview(
                modifier = Modifier.fillMaxWidth(),
                recipe = recipe,
                expanded = index == expandedRecipeIndex.value
            ) {
                expandedRecipeIndex.value = index
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun RecipeOverview(
    modifier: Modifier,
    recipe: Recipe,
    expanded: Boolean,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(enabled = !expanded) { onExpandClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 15.dp),
            text = recipe.name,
            style = appTypography.h6.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color(AppTheme.Colors.black)
            )
        )
        AnimatedVisibility(visible = expanded) {
            Column {
                recipe.ingredients.forEach { ingredient ->
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = ingredient.name,
                            style = appTypography.body2.copy(
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = ingredient.nutritionalValue.quantity.toString(),
                            style = appTypography.body2.copy(
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

