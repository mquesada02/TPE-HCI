package ar.edu.itba.hci.fiit_mobile.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import ar.edu.itba.hci.fiit_mobile.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userDataSource = application.userDataSource
    val routineDataSource = application.routineDataSource
    return ViewModelFactory(
        sessionManager,
        userDataSource,
        routineDataSource,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}