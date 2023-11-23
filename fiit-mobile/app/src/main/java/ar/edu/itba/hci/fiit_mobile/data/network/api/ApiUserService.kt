package ar.edu.itba.hci.fiit_mobile.data.network.api

import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkEmail
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkEmailCode
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecution
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUser
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserCredentials
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.user.NetworkUserToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUserService {
    @GET("users")//PUEDE TENER PARAMETROS (habria que agregarlos)
    suspend fun getUsers(): Response<NetworkUser>

    @POST("users")
    suspend fun addUser(
        @Body userInfo: NetworkUserInformation
    ): Response<NetworkUserContent>

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): Response<NetworkUser>

    @POST("users/resend_verification")
    suspend fun resendVerification(
        @Body email: NetworkEmail
    ): Response<Unit>

    @POST("users/verify_email")
    suspend fun verifyEmail(
        @Body emailAndCode: NetworkEmailCode
    ): Response<Unit>

    @POST("users/login")
    suspend fun logUserIn(
        @Body userCredentials: NetworkUserCredentials
    ): Response<NetworkUserToken>

    @POST("users/logout")
    suspend fun logUserOut(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUserContent>

    @PUT("users/current")
    suspend fun modifyCurrentUser(
        @Body userInfo: NetworkUserInformation
    ): Response<NetworkUserContent>

    @DELETE("users/current")
    suspend fun removeCurrentUser(): Response<Unit>

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