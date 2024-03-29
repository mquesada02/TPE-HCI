package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun RoutinesScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    var fetchedRoutines by remember { mutableStateOf(false) }

    if (!fetchedRoutines) {
        viewModel.getCurrentRoutines()
        fetchedRoutines = true
    }

    val uiState = viewModel.uiState

    Row(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            if(uiState.currentRoutines?.content?.isEmpty() != false) {
                CircularProgressIndicator()
            } else {
                RoutineScroller(onNavigateToScreen = onNavigateToScreen, name = stringResource(R.string.MyRouts), routines = uiState.currentRoutines!!.content )
            }

        }
    }


}