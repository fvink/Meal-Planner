package com.vinks.mealplanner.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow

actual abstract class BaseViewModel actual constructor() : ViewModel() {

    actual abstract val state: StateFlow<ViewState>

    protected actual val vmScope = viewModelScope

    actual override fun onCleared() {}
}