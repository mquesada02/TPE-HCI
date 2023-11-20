package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Components.Order
import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllFavourites
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun FavsScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())) {

    val uiState = viewModel.uiState
    Column() {
        Row() {
            Text(text = stringResource(R.string.Favs))
            Order()
        }

        //esto esta mal porq no sigue ningun orden dado por el usuario todo
        if (uiState.canGetAllFavourites) {
            uiState.favourites?.let {
                RoutineScroller(
                    name = stringResource(R.string.Favs),
                    routines = it
                )
            }
        }
    }
}