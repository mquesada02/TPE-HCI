package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiRoutineService
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycle
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.cycleExercises.NetworkCycleExerciseInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExercise
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseImageInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.exercises.NetworkExerciseInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycleInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routineCycles.NetworkRoutineCycles
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkReview
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineInformation
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineMetadata
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class RoutineDataSource(
    private val sessionManager: SessionManager,
    private val apiRoutineService: ApiRoutineService
): RemoteDataSource() {

    suspend fun getFavs() : NetworkRoutines {
        return handleApiResponse { apiRoutineService.getFavourites() }
    }

    suspend fun addToFavs(id : Int){
        apiRoutineService.addToFavourites(id)
    }

    suspend fun removeFromFavs(id : Int) {
        apiRoutineService.RemoveFromFavourites(id)
    }
    suspend fun getRoutines(): NetworkRoutines{
        return handleApiResponse { apiRoutineService.getRoutines() }
    }

    suspend fun getCurrentRoutines(): NetworkRoutines{
        return handleApiResponse { apiRoutineService.getCurrentRoutines() }
    }

    suspend fun getRoutines(categoryId: Int, userId: Int, difficulty: String, score: Int, orderBy: String): NetworkRoutines{
        return handleApiResponse { apiRoutineService.getRoutines(categoryId, userId, difficulty, score, orderBy) }
    }

    suspend fun getRoutineById(routineId: Int): NetworkRoutineContent{
        return handleApiResponse { apiRoutineService.getRoutineById(routineId) }
    }

    private fun intensityToDifficulty(intensity: String): String{
        if(intensity == "Alta"){
            return "expert"
        }
        if(intensity == "Media"){
            return "intermediate"
        }
        return "rookie"
    }

    suspend fun addRoutine(name: String, detail: String, isPublic: Boolean, intensity: String, muscles: ArrayList<String>, goals: ArrayList<String>, material: ArrayList<String>, imgUrl: String):NetworkRoutineContent{
        return handleApiResponse {
            apiRoutineService.addRoutine(NetworkRoutineInformation(name, detail, isPublic, intensityToDifficulty(intensity), null, NetworkRoutineMetadata(muscles, goals, material, imgUrl)))
        }
    }

    suspend fun getRoutineCycles(routineId: Int):NetworkRoutineCycles{
        return handleApiResponse { apiRoutineService.getRoutineCycles(routineId) }
    }

    suspend fun getRoutineCycleById(routineId: Int, cycleId: Int): NetworkCycleContent {
        return handleApiResponse { apiRoutineService.getRoutineCycleById(routineId, cycleId) }
    }

    suspend fun addRoutineCycle(routineId: Int, name: String, detail: String, type: String, order: Int, repetitions: Int): NetworkCycleContent{
        return handleApiResponse { apiRoutineService.addRoutineCycle(routineId, NetworkRoutineCycleInformation(name, detail, type, order, repetitions, null)) }
    }

    suspend fun getCycleExercises(cycleId: Int): NetworkCycle{
        return handleApiResponse { apiRoutineService.getCycleExercises(cycleId) }
    }

    suspend fun addCycleExercise(cycleId: Int, exerciseId: Int, order: Int, duration: Int, repetitions: Int): NetworkCycleContent{
        return handleApiResponse { apiRoutineService.addCycleExercise(cycleId, exerciseId, NetworkCycleExerciseInformation(order, duration, repetitions)) }
    }

    suspend fun getExercises(): NetworkExercise{
        return handleApiResponse { apiRoutineService.getExercises() }
    }

    suspend fun addExercise(name: String, detail: String, type: String, imgUrl: String): NetworkExerciseContent{
        val addExerciseResponse = handleApiResponse { apiRoutineService.addExercise(
            NetworkExerciseInformation(name, detail, type, null)
        ) }
        handleApiResponse { apiRoutineService.addExerciseImg(addExerciseResponse.id, NetworkExerciseImageInformation(1, imgUrl)) }
        return addExerciseResponse
    }

    suspend fun getExerciseImg(exerciseId: Int): String{
        val getExerciseImgResponse = handleApiResponse { apiRoutineService.getExerciseImg(exerciseId) }
        return getExerciseImgResponse.content[0].url
    }

    suspend fun modifyReview(routineId : Int, info : NetworkReview){
        handleApiResponse { apiRoutineService.modifyReview(routineId, info) }
    }
}