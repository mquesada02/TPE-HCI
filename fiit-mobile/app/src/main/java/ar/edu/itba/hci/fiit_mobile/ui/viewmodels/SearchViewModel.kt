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
import ar.edu.itba.hci.fiit_mobile.ui.states.SearchUiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel (
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
        .onEach { uiState = uiState.copy(isLoading = false) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _routines.value
        )

    fun updateLoad() {
        uiState = uiState.copy(isLoading = false)
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
    }.invokeOnCompletion { uiState = uiState.copy(isLoading = false) }

    fun orderRoutines(order:String) {
        if(order == "date") {
            _routines.value.sortBy { it.date }
        }
        if(order == "difficulty") {
            _routines.value.sortByDescending { it.difficulty }
        }
        if(order == "score") {
            _routines.value.sortByDescending { it.score }
        }
        if(order == "username") {
            _routines.value.sortBy { it.user.username }
        }
        if(order == "routinename") {
            _routines.value.sortBy { it.name }
        }
    }
}