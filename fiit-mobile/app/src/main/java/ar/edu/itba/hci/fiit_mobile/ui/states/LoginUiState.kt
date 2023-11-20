package ar.edu.itba.hci.fiit_mobile.ui.states

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkError
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserContent

data class LoginUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: NetworkUserContent? = null,
    val error: NetworkError? = null
) {
    val LoginUiState.canGetCurrentUser: Boolean get() = isAuthenticated

}
