package com.vinks.mealplanner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.model.MealPlan
import com.vinks.mealplanner.domain.repository.IngredientRepository
import com.vinks.mealplanner.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    ingredientRepository: IngredientRepository,
    mealPlanRepository: MealPlanRepository
) : ViewModel() {

    private val ingredients = ingredientRepository.getAllIngredients(refreshCache = true)
    private val mealPlans = mealPlanRepository.getAllMealPlans(refreshCache = true)

    val uiState: StateFlow<List<MealPlan>> = mealPlans
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = emptyList(),
        )

}