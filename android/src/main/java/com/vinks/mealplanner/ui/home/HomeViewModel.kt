package com.vinks.mealplanner.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinks.mealplanner.domain.model.Ingredient
import com.vinks.mealplanner.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    ingredientRepository: IngredientRepository
) : ViewModel() {

    private val ingredients = ingredientRepository.getAllIngredients(refreshCache = true)

    val uiState: StateFlow<List<Ingredient>> = ingredients
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = emptyList(),
        )

}