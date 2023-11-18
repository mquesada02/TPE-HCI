package ar.edu.itba.hci.fiit_mobile.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.itba.hci.fiit_mobile.Components.RoutineScroller
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Components.Order

@Composable
fun FavsScreen(){

    Column(){
        Row(){
            Text( text = stringResource(R.string.Favs))
            Order()
        }
       // RoutineScroller(name = , viewModel = ) todo
    }

}