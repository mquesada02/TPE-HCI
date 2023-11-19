package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiUserService
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserCredentials
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserInformation
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

    suspend fun register(username: String, password: String, firstName: String, lastName: String, birthdate: Int, email: String): NetworkUserContent {
        return handleApiResponse { apiUserService.addUser(NetworkUserInformation(username, password, firstName, lastName, null, birthdate, email, null, null, null)) }
    }

    suspend fun getCurrentUserRoutines(): NetworkRoutines{
        return handleApiResponse { apiUserService.getCurrentUserRoutines() }
    }

    suspend fun getUserRoutines(userId: Int): NetworkRoutines{
        return handleApiResponse { apiUserService.getUserRoutines(userId) }
    }

    suspend fun modifyCurrentUser(userInfo: NetworkUserInformation): NetworkUserContent{
        return handleApiResponse { apiUserService.modifyCurrentUser(userInfo) }
    }
}

