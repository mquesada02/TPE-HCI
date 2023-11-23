package ar.edu.itba.hci.fiit_mobile.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.hci.fiit_mobile.data.DataSourceException
import ar.edu.itba.hci.fiit_mobile.data.network.RoutineDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.UserDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkReview
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.states.HomeUiState
import ar.edu.itba.hci.fiit_mobile.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel (
    sessionManager: SessionManager,
    private val userDataSource: UserDataSource,
    private val routineDataSource: RoutineDataSource
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))

    fun addFavs(id: Int) = runOnViewModelScope(
        { routineDataSource.addToFavs(id) },
        { state, _ -> state.copy() }
    )


    fun removeFavs(id: Int) = runOnViewModelScope(
        { routineDataSource.removeFromFavs(id) },
        { state, _ -> state.copy() }
    )

    private fun callExecution(id: Int, userId: Int) = runOnViewModelScope(
        { routineDataSource.addExecution(id, userId) },
        { state, _ -> state.copy() }
    )

    fun addExecution(id: Int) : Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            userDataSource.getCurrentUser()
        }.onSuccess { response ->
            uiState = uiState.copy(isFetching = false)
            callExecution(id, response.id!!)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    fun executionsWithRoutines() : Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            routineDataSource.getCurrentRoutines()
        }.onSuccess { response ->
            uiState = uiState.copy(isFetching = false)
            for(routine in response.content){
                getExecutions(routine.id)
            }
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    fun getExecutions(routineId: Int) : Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            routineDataSource.getExecutions(routineId)
        }.onSuccess { response ->

            for(execution in response.content) {
                if (execution.duration == uiState.currentUser!!.id) {
                    /*var found = false
                    val elementsToRemove = Stack<NetworkExecutionContent>()
                    val elementsToAdd = Stack<NetworkExecutionContent>()
                    for(routine in uiState.recents){
                        if(routine.routine!!.id == routineId ){
                            found = true
                            if (routine.date < execution.date) {
                                elementsToRemove.push(routine)
                                elementsToAdd.push(execution)
                            }
                        }
                    }
                    while(!elementsToRemove.empty()){
                        uiState.recents.remove(elementsToRemove.pop())
                    }
                    if (!found) {
                        elementsToAdd.push(execution)
                    }
                    while(!elementsToAdd.empty()){
                        uiState.recents.add(elementsToAdd.pop())
                    }*/
                    uiState.recents.add(execution)
                    uiState.recents.sortByDescending  { it.date }
                }
            }
            uiState = uiState.copy(isFetching = false, isFetchingRecents = true)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    fun gotFetchRecents() {
        uiState = uiState.copy(isFetchingRecents = false)
    }


    fun updateScore(id: Int, info : NetworkReview) = runOnViewModelScope(
        { routineDataSource.modifyReview(id, info)},
        {state, _ -> state.copy()}
    )
    fun getCurrentUser() = runOnViewModelScope(
        { userDataSource.getCurrentUser() },
        { state, response -> state.copy(currentUser = response) }
    )

    fun getCurrentExecutions() = runOnViewModelScope(
        { routineDataSource.getCurrentExecutions() },
        { state, response -> state.copy(recents = response.content) }
    )

    fun getRoutines() = runOnViewModelScope(
        { routineDataSource.getRoutines() },
        { state, response -> state.copy(routines = response) }
    )

    fun getRoutine(id: Int) = runOnViewModelScope(
        { routineDataSource.getRoutineById(id) },
        { state, response -> state.copy(currentRoutine = response) }
    )

    fun getCurrentRoutines() = runOnViewModelScope(
        { routineDataSource.getCurrentRoutines() },
        { state, response -> state.copy(currentRoutines = response) }
    )

    fun updateData(data: NetworkRoutineContent) {
        uiState = uiState.copy(currentRoutine = data)
    }

    fun getFavourites() = runOnViewModelScope(
        { routineDataSource.getFavs() },
        { state, response -> state.copy(favourites = response) }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (HomeUiState, R) -> HomeUiState
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

    private fun handleError(e: Throwable): NetworkError{
        return if (e is DataSourceException) {
            NetworkError(e.code, e.message ?: "", e.details)
        } else {
            NetworkError(null, e.message ?: "", null)
        }
    }
}