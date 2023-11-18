package ar.edu.itba.hci.fiit_mobile.data.network.api

import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkCycleInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkCycles
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkExecution
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkExecutionContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkExecutionModification
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutineInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//get/post/put/delete notacion:
//@GET("route")
interface ApiRoutineService {
    //------ROUTINES---------------------------------------------
    @GET("routines")
    suspend fun getRoutines(
        @Query("categoryId") categoryId: Int? = null,
        @Query("userId") userId: Int? = null,
        @Query("difficulty") difficulty: String? = null, //rookie, beginner, intermediate, advanced, expert
        @Query("score") score: Int? = null,
        @Query("orderBy") orderBy: String? = null //id, name, detail, date, score, difficulty, category, user
    ): Response<NetworkRoutines>

    @GET("routines")
    suspend fun getRoutines(): Response<NetworkRoutines>

    @POST("routines")
    suspend fun addRoutine(
        @Body routineInfo: NetworkRoutineInformation
    ): Response<NetworkRoutineContent>

    @GET("routines/{routineId}")
    suspend fun getRoutineById(
        @Path("routineId") routineId: Int
    ): Response<NetworkRoutineContent>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(
        @Path("routineId") routineId: Int,
        @Body routineInfo: NetworkRoutineInformation
    ): Response<NetworkRoutineContent>

    @DELETE("routines/{routineId}")
    suspend fun removeRoutine(
        @Path("routineId") routineId: Int
    ): Response<Unit>

    //------ROUTINE CYCLES---------------------------------------------

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(
        @Path("routineId") routineId: Int
    ): Response<NetworkCycles>

    @POST("routines/{routineId}/cycles")
    suspend fun addRoutineCycle(
        @Path("routineId") routineId: Int,
        @Body cycleInfo: NetworkCycleInformation
    ): Response<NetworkCycleContent>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getRoutineCyclesById(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<NetworkCycleContent>

    @PUT("routines/{routineId}/cycles/{cycleId}")
    suspend fun modifyRoutineCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int,
        @Body cycleInfo: NetworkCycleInformation
    ): Response<NetworkCycleInformation>

    @DELETE("routines/{routineId}/cycles/{cycleId}")
    suspend fun removeRoutineCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<Unit>
    //------CYCLE EXERCISES---------------------------------------------


    //------EXERCISES---------------------------------------------


    //------EXERCISE IMAGES---------------------------------------------


    //------EXERCISE VIDEOS---------------------------------------------


    //------FAVOURITES---------------------------------------------
    @GET("favourites")
    suspend fun getFavourites(): Response<NetworkRoutines>

    @POST("favourites/{routineId}")
    suspend fun addToFavourites(
        @Path("routineId") routineId: Int
    )

    @DELETE("favourites/{routineId}")
    suspend fun RemoveFromFavourites(
        @Path("routineId") routineId: Int
    )

    //------EXECUTIONS---------------------------------------------
    @GET("executions/{routineId}")
    suspend fun getRoutineExecutions(
        @Path("routineId") routineId: Int
    ): Response<NetworkExecution>

    @POST("executions/{routineId}")
    suspend fun addRoutineExecution(
        @Path("routineId") routineId: Int,
        @Body duration: NetworkExecutionModification
    ): Response<NetworkExecutionContent>
}