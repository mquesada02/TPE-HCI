package ar.edu.itba.hci.fiit_mobile.ui.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: NetworkError? = null
){
    val SearchUiState.hasError: Boolean get() = error != null
}
