package ar.edu.itba.hci.fiit_mobile.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.hci.fiit_mobile.data.DataSourceException
import ar.edu.itba.hci.fiit_mobile.data.network.RoutineDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.states.LoginUiState
import ar.edu.itba.hci.fiit_mobile.ui.states.SearchUiState
import ar.edu.itba.hci.fiit_mobile.util.SessionManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel (
    sessionManager: SessionManager,
    private val routineDataSource: RoutineDataSource
): ViewModel() {
    var uiState by mutableStateOf(SearchUiState())
        private set

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _routines = MutableStateFlow(ArrayList<NetworkRoutineContent>())
    val routines = searchText
        .debounce(500L)
        .onEach { uiState = uiState.copy(isLoading = true) }
        .combine(_routines){ text, routines ->
            if(text.isBlank()){
                routines
            } else{
                routines.filter {
                    it.matchesStringQuery(text)
                }
            }
        }
        .onEach { uiState = uiState.copy(isLoading = true) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _routines.value
        )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (SearchUiState, R) -> SearchUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isLoading = false)
        }.onFailure { e ->
            uiState = uiState.copy(isLoading = false, error = handleError(e))
        }
    }

    private fun runOnViewModelScopeRoutines(
        block: suspend () -> ArrayList<NetworkRoutineContent>,
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            _routines.value = response
            uiState = uiState.copy(isLoading = false)
        }.onFailure { e ->
            uiState = uiState.copy(isLoading = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): NetworkError {
        return if (e is DataSourceException) {
            NetworkError(e.code, e.message ?: "", e.details)
        } else {
            NetworkError(null, e.message ?: "", null)
        }
    }
    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    fun getRoutines() = runOnViewModelScopeRoutines {
        routineDataSource.getRoutines().content
    }
}