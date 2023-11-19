package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiUserService
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserCredentials
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkEmailCode
import ar.edu.itba.hci.fiit_mobile.util.SessionManager
import java.text.SimpleDateFormat
import java.util.Date

interface Metadata {
    val weight: Int
    val height: Int
}
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

    suspend fun register(email: String, name: String, surname: String, username: String, password: String, birthdate: String, weight: String, height: String) {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date: Date? = formatter.parse(birthdate)

        val metadata = object : Metadata {
            override val weight: Int = weight.toInt()
            override val height: Int = height.toInt()
        }
        handleApiResponse { apiUserService.addUser(NetworkUserInformation(username, password, name, surname, "other", date, email, "", "", metadata)) }
    }

    suspend fun verifyEmail(email: String, code: String) {
        handleApiResponse { apiUserService.verifyEmail(NetworkEmailCode(email, code)) }
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