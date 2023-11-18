package ar.edu.itba.hci.fiit_mobile

import android.app.Application
import ar.edu.itba.hci.fiit_mobile.util.SessionManager

class MyApplication : Application() {

//    val userRemoteDataSource: UserRemoteDataSource
//        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))
//
//    val sportRemoteDataSource: SportRemoteDataSource
//        get() = RoutineRemoteDataSource(RetrofitClient.getApiSportService(this))
    /*TODO*/

    val sessionManager: SessionManager
        get() = SessionManager(this)

}