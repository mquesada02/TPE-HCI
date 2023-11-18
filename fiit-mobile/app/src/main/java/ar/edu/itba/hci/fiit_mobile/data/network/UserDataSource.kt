package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiUserService
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserCredentials
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class UserDataSource(
    private val sessionManager: SessionManager,
    private val apiUserService: ApiUserService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.logUserIn(NetworkUserCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logUserOut() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUserContent {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }
}