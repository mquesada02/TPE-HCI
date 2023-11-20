package ar.edu.itba.hci.fiit_mobile.data.network.api

import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycle
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleExerciseInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycleInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycles
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecution
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecutionContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.executions.NetworkExecutionModification
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExercise
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseImageContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseImageInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseImages
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
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
    ): Response<NetworkRoutineCycles>

    @POST("routines/{routineId}/cycles")
    suspend fun addRoutineCycle(
        @Path("routineId") routineId: Int,
        @Body cycleInfo: NetworkRoutineCycleInformation
    ): Response<NetworkCycleContent>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getRoutineCycleById(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<NetworkCycleContent>

    @PUT("routines/{routineId}/cycles/{cycleId}")
    suspend fun modifyRoutineCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int,
        @Body cycleInfo: NetworkRoutineCycleInformation
    ): Response<NetworkRoutineCycleInformation>

    @DELETE("routines/{routineId}/cycles/{cycleId}")
    suspend fun removeRoutineCycle(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<Unit>

    //------CYCLE EXERCISES---------------------------------------------

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(
        @Path("cycleId") cycleId: Int
    ): Response<NetworkCycle>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExerciseById(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int
    ): Response<NetworkCycleContent>

    @POST("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun addCycleExercise(
        @Path("cycleId") cycleId: Int,
        @Path("exerciseId") exerciseId: Int,
        @Body cycleExerciseInformation: NetworkCycleExerciseInformation
    ): Response<NetworkCycleContent>

    //------EXERCISES---------------------------------------------
    @GET("exercises")
    suspend fun getExercises(): Response<NetworkExercise>

    @POST("exercises")
    suspend fun addExercise(
        @Body exerciseInformation: NetworkExerciseInformation
    ): Response<NetworkExerciseContent>

    //------EXERCISE IMAGES---------------------------------------------
    @GET("exercises/{exerciseId}/images")
    suspend fun getExerciseImg(
        @Path("exerciseId") exerciseId: Int
    ): Response<NetworkExerciseImages>

    @POST("exercises/{exerciseId}/images")
    suspend fun addExerciseImg(
        @Path("exerciseId") exerciseId: Int,
        @Body exerciseImageInformation: NetworkExerciseImageInformation
    ): Response<NetworkExerciseImageContent>

    //------EXERCISE VIDEOS---------------------------------------------


    //------FAVOURITES---------------------------------------------
    @GET("favourites")
    suspend fun getFavourites() : Response<NetworkRoutines>

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