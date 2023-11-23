package ar.edu.itba.hci.fiit_mobile.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.hci.fiit_mobile.util.SessionManager
import ar.edu.itba.hci.fiit_mobile.data.network.UserDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.ui.states.LoginUiState
import ar.edu.itba.hci.fiit_mobile.data.DataSourceException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*
*  Acá se encuentran funciones usadas en Login, ConfirmEmail y Register
*/

class LoginViewModel(
    sessionManager: SessionManager,
    private val userDataSource: UserDataSource,
//    private val routineDataSource: RoutineDataSource
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope(
        { userDataSource.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun logout() = runOnViewModelScope(
        { userDataSource.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                //routines = null
                // TODO
            )
        }
    )

    fun register(email: String, name: String, surname: String, username: String, password: String, birthdate: String, weight: String, height: String) = runOnViewModelScope(
        { userDataSource.register(email, name, surname, username, password, birthdate, weight, height) },
        { state, _ -> state.copy() }
    )

    fun verifyEmail(email: String, code: String) = runOnViewModelScope(
        { userDataSource.verifyEmail(email, code) },
        { state, _ -> state.copy() }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userDataSource.getCurrentUser() },
        { state, response -> state.copy(currentUser = response) }
    )

    fun updateStateError() {
        uiState = uiState.copy(error = null)
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (LoginUiState, R) -> LoginUiState
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