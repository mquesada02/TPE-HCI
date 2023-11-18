package ar.edu.itba.hci.fiit_mobile.data.network

import ar.edu.itba.hci.fiit_mobile.data.network.api.ApiRoutineService
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class RoutineDataSource(
    private val sessionManager: SessionManager,
    private val apiRoutineService: ApiRoutineService
): RemoteDataSource() {

}