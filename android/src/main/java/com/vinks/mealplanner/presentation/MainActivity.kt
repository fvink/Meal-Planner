package com.vinks.mealplanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.vinks.mealplanner.presentation.home.HomeScreen
import com.vinks.mealplanner.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    @ExperimentalAnimationApi
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ProvideWindowInsets {
                    HomeScreen(homeViewModel)
                }
            }
        }
    }
}
