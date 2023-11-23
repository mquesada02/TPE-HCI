package ar.edu.itba.hci.fiit_mobile.ui.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecutionContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserContent

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val isFetchingRecents: Boolean = false,
    val currentUser: NetworkUserContent? = null,
    val routines: NetworkRoutines? = null,
    val currentRoutines: NetworkRoutines? = null,
    val currentRoutine : NetworkRoutineContent? = null,
    val favourites: NetworkRoutines? = null,
    val error: NetworkError? = null,
    val ratings: NetworkRoutines? = null,
    val recents: ArrayList<NetworkExecutionContent> = arrayListOf(),
    val featured: NetworkRoutines? = null
)
val HomeUiState.canGetAllFavourites: Boolean get() = isAuthenticated
val HomeUiState.canGetAllRoutines: Boolean get() = isAuthenticated
val HomeUiState.canGetCurrentFav: Boolean get() = isAuthenticated
val HomeUiState.canAddFav: Boolean get() = isAuthenticated
val HomeUiState.canModifyFavs: Boolean get() = isAuthenticated
val HomeUiState.canDeleteFav: Boolean get() = canModifyFavs
val HomeUiState.canGetUser: Boolean get() = isAuthenticated

