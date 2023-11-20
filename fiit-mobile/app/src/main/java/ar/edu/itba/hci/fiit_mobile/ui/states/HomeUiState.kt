package ar.edu.itba.hci.fiit_mobile.ui.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: NetworkUser? = null,
    val routines: NetworkRoutines? = null,
    val favourites: NetworkRoutines? = null,
    val currentFav: NetworkRoutineContent? = null,
    val error: NetworkError? = null
)
val HomeUiState.canGetAllFavourites: Boolean get() = isAuthenticated
val HomeUiState.canGetAllRoutines: Boolean get() = isAuthenticated
val HomeUiState.canGetCurrentFav: Boolean get() = isAuthenticated && currentFav != null
val HomeUiState.canAddFav: Boolean get() = isAuthenticated && currentFav == null
val HomeUiState.canModifyFavs: Boolean get() = isAuthenticated && currentFav != null
val HomeUiState.canDeleteFav: Boolean get() = canModifyFavs
val HomeUiState.canGetUser: Boolean get() = isAuthenticated

