package ar.edu.itba.hci.fiit_mobile.Views

import androidx.compose.runtime.Composable
//import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import ar.edu.itba.hci.fiit_mobile.Components.Order
import ar.edu.itba.hci.fiit_mobile.Components.SearchByName


@Composable
fun SearcherScreen(){

    Column(){
        Row(){
            SearchByName()
            Order()
        }
        //RoutineScroller(name = , viewModel = ) todo
    }

}