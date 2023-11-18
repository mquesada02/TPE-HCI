package ar.edu.itba.hci.fiit_mobile.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines

data class RoutineState(
    val routines: NetworkRoutines? = null,
    val isLoading: Boolean = false,
    val errMessage: String? = null
)

//si tiene un error tiene un mensaje de error
val RoutineState.hasError: Boolean get() = errMessage != null