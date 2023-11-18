package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkCycleInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkEmail
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkEmailCode
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkExecution
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUser
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserCredentials
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkUserToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiUserService {
    @GET("users")//PUEDE TENER PARAMETROS (habria que agregarlos)
    suspend fun getUsers(): Response<NetworkUser>

    @POST("users")
    suspend fun addUser(
        @Body userInfo: NetworkCycleInformation
    ): Response<NetworkUserContent>

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): Response<NetworkUser>

    @POST("users/resend_verification")
    suspend fun resendVerification(
        @Body email: NetworkEmail
    )

    @POST("users/verify_email")
    suspend fun verifyEmail(
        @Body emailAndCode: NetworkEmailCode
    )

    @POST("users/login")
    suspend fun logUserIn(
        @Body userCredentials: NetworkUserCredentials
    ): Response<NetworkUserToken>

    @POST("users/logout")
    suspend fun logUserOut()

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUserContent>

    @PUT("users/current")
    suspend fun modifyCurrentUser(
        @Body userInfo: NetworkUserInformation
    ): Response<NetworkUserContent>

    @DELETE("users/current")
    suspend fun removeCurrentUser()

    @GET("users/current/routines") //PUEDE TENER PARAMETROS (habria que agregarlos)
    suspend fun getCurrentUserRoutines(): Response<NetworkRoutines>

    @GET("users/{userId}}/routines") //PUEDE TENER OTROS PARAMETROS (habria que agregarlos)
    suspend fun getUserRoutines(
        @Path("userId") userId: Int
    ): Response<NetworkRoutines>

    @GET("users/current/executions") //PUEDE TENER PARAMETROS (habria que agregarlos)
    suspend fun getCurrentUserExecutions():
            Response<NetworkExecution>

}