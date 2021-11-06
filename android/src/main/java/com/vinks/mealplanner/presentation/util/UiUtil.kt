package com.vinks.mealplanner.presentation.util

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Context.screenWidth(): Dp {
    val displayMetrics = resources.displayMetrics
    return (displayMetrics.widthPixels / displayMetrics.density).dp
}

fun Context.screenHeight(): Dp {
    val displayMetrics = resources.displayMetrics
    return (displayMetrics.heightPixels / displayMetrics.density).dp
}