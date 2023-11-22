package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen

@Composable
fun RoutinesScreen(onNavigateToScreen: (String) -> Unit){

    var fetchedRoutines by remember { mutableStateOf(false) }

    

    Column(){
        Row {
            Text( text = stringResource(R.string.MyRouts))
        }
        /* En espera del orderBy button
            RoutineScroller(name = , viewModel = ) todo
        */
    }

}