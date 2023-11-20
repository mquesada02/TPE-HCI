package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.hci.fiit_mobile.Components.Order
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
            Text( text = stringResource(R.string.MyRouts))
            Order()
        }
        // RoutineScroller(name = , viewModel = ) todo

}