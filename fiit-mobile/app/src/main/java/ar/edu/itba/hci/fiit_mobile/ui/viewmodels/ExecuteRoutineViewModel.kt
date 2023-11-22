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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ExecuteRoutineViewModel(
    private val routineDataSource: RoutineDataSource
) : ViewModel() {

    var uiState by mutableStateOf(ExecuteRoutineUiState())
        private set

    fun updateCycles(routineId: Int) = runOnViewModelScope(
        { routineDataSource.getRoutineCycles(routineId) },
        { state, response -> state.copy(cycles = response.content, totalCycles = response.totalCount)}
    )

    fun switchFetch() {
        uiState = uiState.copy(isFetching = !uiState.isFetching)
    }

    fun getExercises(cycleId: Int) = runOnViewModelScope(
        { routineDataSource.getCycleExercises(cycleId) },
        { state, response -> state.copy(cycleExercises = response.content, totalExercises = response.totalCount)}
    )

    private fun update(cycleId: Int) = runOnViewModelScopeExercises(
        { routineDataSource.getCycleExercises(cycleId) },
        { state, response -> state.copy(cycleExercises = response.content, totalExercises = response.totalCount)}
    )

    private fun updateExercises() {
        if (uiState.cycleIndex >= uiState.totalCycles - 1) {
            return
        } else {
            uiState = uiState.copy(cycleIndex = uiState.cycleIndex + 1)
            update(uiState.cycles[uiState.cycleIndex].id)
        }
    }

    fun nextIndex() {
        if (uiState.exerciseIndex >= uiState.totalExercises - 1) {
            uiState = uiState.copy(exerciseIndex = 0)
            if (uiState.cycles[uiState.cycleIndex].repetitions == 1) {
                updateExercises()
            } else {
                uiState.cycles[uiState.cycleIndex].repetitions--
            }

            //peekNextExerciseName()
            //peekNextExerciseImage()
        } else {
            uiState = uiState.copy(exerciseIndex = uiState.exerciseIndex + 1)
            //peekNextExerciseName()
            //peekNextExerciseImage()
            getExerciseImage(uiState.cycleExercises[uiState.exerciseIndex].exercise.id)
        }


    }

    private fun resetExercises() {
        if (uiState.cycleIndex == 0) {
            return
        } else {
            uiState = uiState.copy(cycleIndex = uiState.cycleIndex - 1)
            getExercises(uiState.cycles[uiState.cycleIndex].id)
        }

    }

    /*fun previousIndex() {
        if (uiState.exerciseIndex == 0) {
            resetExercises()
            uiState = uiState.copy(exerciseIndex = uiState.totalExercises - 1)
        } else {
            uiState = uiState.copy(exerciseIndex = uiState.exerciseIndex - 1)
        }
    }*/

   /* fun peekNextExerciseName() {
        uiState = if (uiState.exerciseIndex >= uiState.totalExercises - 1) {
            uiState.copy(nextExerciseName = "-")
        } else {
            uiState.copy(nextExerciseName = uiState.cycleExercises[uiState.exerciseIndex + 1].exercise.name)
        }
    }*/

    /*fun peekPreviousExerciseName() {
        uiState = if (uiState.exerciseIndex == 0) {
            uiState.copy(nextExerciseName = "-")
        } else {
            uiState.copy(nextExerciseName = uiState.cycleExercises[uiState.exerciseIndex].exercise.name)
        }
    }*/

    fun swapCycles() {
        val swap = uiState.cycles[1]
        for(i in 1 until uiState.totalCycles - 1) {
            uiState.cycles[i] = uiState.cycles[i+1]
        }
        uiState.cycles[uiState.totalCycles - 1] = swap
    }

    /*fun peekPreviousExerciseImage() {
        if (uiState.exerciseIndex == 0) {
            uiState = uiState.copy(nextExerciseImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Image_not_available.png/640px-Image_not_available.png")
        } else {
            getExerciseImage(uiState.cycleExercises[uiState.exerciseIndex].exercise.id)
        }
    }*/

    /*fun peekNextExerciseImage() {
        if (uiState.exerciseIndex >= uiState.totalExercises - 1) {
            uiState = uiState.copy(nextExerciseImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Image_not_available.png/640px-Image_not_available.png")
        } else {
            getExerciseImage(uiState.cycleExercises[uiState.exerciseIndex + 1].exercise.id)
        }
    }
*/

    fun getExerciseImage(exerciseId: Int) = runOnViewModelScope(
        { routineDataSource.getExerciseImg(exerciseId) },
        { state, response -> state.copy(nextExerciseImage = response) }
    )

    fun hasNext() : Boolean {
        return (uiState.cycleIndex < uiState.totalCycles - 1) || (uiState.cycleIndex == uiState.totalCycles - 1 && (uiState.exerciseIndex < uiState.totalExercises - 1 || uiState.cycles[uiState.cycleIndex].repetitions > 1))
    }

   /* fun hasPrevious() : Boolean {
        return (uiState.cycleIndex > 0) || (uiState.cycleIndex == 0 && uiState.exerciseIndex > 0)
    }
*/
    private fun <R> runOnViewModelScopeExercises(
        block: suspend () -> R,
        updateState: (ExecuteRoutineUiState, R) -> ExecuteRoutineUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
            getExerciseImage(uiState.cycleExercises[uiState.exerciseIndex].exercise.id)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
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