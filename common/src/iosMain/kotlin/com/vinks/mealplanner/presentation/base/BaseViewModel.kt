package com.vinks.mealplanner.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

actual abstract class BaseViewModel actual constructor() {

    private val viewModelJob = SupervisorJob()

    protected actual val vmScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    actual abstract val state: StateFlow<ViewState>

    protected actual open fun onCleared() {
        viewModelJob.cancelChildren()
    }

    fun <T> Flow<T>.observe(onChange: ((T) -> Unit)) {
        onEach {
            onChange(it)
        }.launchIn(
            vmScope
        )
    }
}