package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import ar.edu.itba.hci.fiit_mobile.Components.Order
import ar.edu.itba.hci.fiit_mobile.Components.SearchByName


@Composable
fun SearcherScreen(onNavigateToScreen: (String) -> Unit) {

    Column(){
        Row(){
            SearchByName()
            Order()
        }
        //RoutineScroller(name = , viewModel = ) todo
    }

}