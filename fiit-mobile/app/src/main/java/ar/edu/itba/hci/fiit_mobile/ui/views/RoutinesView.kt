package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen

@Composable
fun RoutinesScreen(onNavigateToScreen: (String) -> Unit){

    Column(){
        Row(){
            Button(onClick = { onNavigateToScreen(Screen.ErrorScreen.route)}){
                Text(text = stringResource(R.string.Create))
            }
        }
        Row {
            Text( text = stringResource(R.string.MyRouts))
        }
        // RoutineScroller(name = , viewModel = ) todo
    }

}