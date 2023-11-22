package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllFavourites
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun FavsScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())) {

    var fetchedRoutines by remember { mutableStateOf(false) }

    if (!fetchedRoutines) {
        viewModel.getFavourites()
        fetchedRoutines = true
    }


    val uiState = viewModel.uiState
    Column {

        //En espera a orderByButton todo
        if (uiState.canGetAllFavourites) {
            if (uiState.favourites?.content?.isEmpty() != false) {
                CircularProgressIndicator()
            } else {
                RoutineScroller(
                    onNavigateToScreen = onNavigateToScreen,
                    name = stringResource(R.string.Favs),
                    routines = uiState.favourites.content
                )
            }

        }
    }
}