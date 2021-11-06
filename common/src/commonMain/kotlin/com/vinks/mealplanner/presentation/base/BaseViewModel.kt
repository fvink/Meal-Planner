package com.vinks.mealplanner.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

expect abstract class BaseViewModel() {

    abstract val state: StateFlow<ViewState>

    protected val vmScope: CoroutineScope

    protected open fun onCleared()
}