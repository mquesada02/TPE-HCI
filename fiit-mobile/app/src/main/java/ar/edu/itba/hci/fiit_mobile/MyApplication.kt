package ar.edu.itba.hci.fiit_mobile

import android.app.Application
import ar.edu.itba.hci.fiit_mobile.data.network.RoutineDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.UserDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.api.RetrofitClient
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class MyApplication : Application() {

    val userDataSource: UserDataSource
        get() = UserDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    val routineDataSource: RoutineDataSource
        get() = RoutineDataSource(sessionManager, RetrofitClient.getApiRoutineService(this))
    /*TODO*/

    val sessionManager: SessionManager
        get() = SessionManager(this)

}