package ar.edu.itba.hci.fiit_mobile.data.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.hci.fiit_mobile.states.RoutineState
import kotlinx.coroutines.launch
import ar.edu.itba.hci.fiit_mobile.data.network.RetrofitClient
import kotlinx.coroutines.Job


class RoutinesViewModel : ViewModel() {
    var routineState by mutableStateOf(RoutineState())
        private set
    //private var fetchJob = Job? = null;

    //esto se podria usar por ejemplo para un refresh
    suspend fun fetchRoutines(){
        /*fetchJob = */
        viewModelScope.launch {
            //isLoading
            routineState = routineState.copy(isLoading = true)
        }
        runCatching {
            var apiRoutineService = RetrofitClient.getApiRoutineService()
            apiRoutineService.getRoutines()
        }.onSuccess { response ->
            routineState = routineState.copy(
                routines = response.body(),
                isLoading = false
            )
        }.onFailure { e ->
            routineState = routineState.copy(
                errMessage = e.message,
                isLoading = false
            )
        }
    }
}