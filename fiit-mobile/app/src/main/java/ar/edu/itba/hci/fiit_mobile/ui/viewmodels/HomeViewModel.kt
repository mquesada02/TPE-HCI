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

    fun getCurrentUser() = runOnViewModelScope(
        { userDataSource.getCurrentUser() },
        { state, response -> state.copy(currentUser = response) }
    )

    // mmm raro nose si lo entendi todo
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