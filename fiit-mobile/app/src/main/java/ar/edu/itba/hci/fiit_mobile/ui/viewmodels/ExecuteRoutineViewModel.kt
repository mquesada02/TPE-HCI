package ar.edu.itba.hci.fiit_mobile.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.hci.fiit_mobile.data.DataSourceException
import ar.edu.itba.hci.fiit_mobile.data.network.RoutineDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.ui.states.ExecuteRoutineUiState
import ar.edu.itba.hci.fiit_mobile.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ExecuteRoutineViewModel(
    sessionManager: SessionManager,
    private val routineDataSource: RoutineDataSource
) : ViewModel() {

    var uiState by mutableStateOf(ExecuteRoutineUiState())
        private set

    fun updateCycles(routineId: Int) = runOnViewModelScope(
        { routineDataSource.getRoutineCycles(routineId) },
        { state, response -> state.copy(cycles = response.content, totalCycles = response.totalCount)}
    )


    fun getExercises(cycleId: Int) = runOnViewModelScope(
        { routineDataSource.getCycleExercises(cycleId) },
        { state, response -> state.copy(cycleExercises = response.content, totalExercises = response.totalCount)}
    )

    private fun updateExercises() {
        if (uiState.cycleIndex >= uiState.totalCycles) {
            return
        }
        getExercises(uiState.cycles[uiState.cycleIndex].id)
        uiState = uiState.copy(cycleIndex = uiState.cycleIndex + 1)
    }

    /*fun getNextExercise(routineId: Int) : NetworkCycleContent {
        if (uiState.exerciseIndex == uiState.totalExercises) {
            *//* fetch for next exercise in the next cycle *//*
            uiState = uiState.copy(exerciseIndex = 0)
            updateExercises()

        }
        val toReturn = uiState.cycleExercises[uiState.exerciseIndex]
        uiState = uiState.copy(exerciseIndex = uiState.exerciseIndex + 1)
        return toReturn
    }*/

    fun getNextExerciseImage(exerciseId: Int) = runOnViewModelScope(
        { routineDataSource.getExerciseImg(exerciseId) },
        { state, response -> state.copy(nextExerciseImage = response)}
    )

    fun hasNext() : Boolean {
        return uiState.cycleIndex < uiState.totalCycles
    }

    fun hasPrevious() : Boolean {
        return uiState.cycleIndex > 0
    }


    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (ExecuteRoutineUiState, R) -> ExecuteRoutineUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): NetworkError {
        return if (e is DataSourceException) {
            NetworkError(e.code, e.message ?: "", e.details)
        } else {
            NetworkError(null, e.message ?: "", null)
        }
    }
}