package ar.edu.itba.hci.fiit_mobile.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ar.edu.itba.hci.fiit_mobile.data.network.RoutineDataSource
import ar.edu.itba.hci.fiit_mobile.data.network.UserDataSource

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userDataSource: UserDataSource,
    private val routineDataSource: RoutineDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
//        when {
//            isAssignableFrom(MainViewModel::class.java) ->
//                MainViewModel(sessionManager, userRepository, sportRepository)
//
//            else ->
//                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//        }
    } as T
    /*TODO*/
}