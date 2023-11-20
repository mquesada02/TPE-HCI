package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiRoutineService
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutines
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class RoutineDataSource(
    private val sessionManager: SessionManager,
    private val apiRoutineService: ApiRoutineService
): RemoteDataSource() {

    suspend fun getRoutines() : NetworkRoutines {
        return handleApiResponse { apiRoutineService.getRoutines() }
    }

    suspend fun getFavs() : NetworkRoutines {
        return handleApiResponse { apiRoutineService.getFavourites() }
    }

    suspend fun addToFavs(id : Int){
        apiRoutineService.addToFavourites(id)
    }

    suspend fun removeFromFavs(id : Int) {
        apiRoutineService.RemoveFromFavourites(id)
    }
}