package ar.edu.itba.hci.fiit_mobile.Views

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

@Composable
@Preview
fun RoutinesScreen(){
    var inDialog by remember { mutableStateOf(false) }

    Column(){
        Row(){
            Button(onClick = {inDialog=true}){
                Text(text = stringResource(R.string.Create))
            }
            if(inDialog) {
                AlertDialog(
                    title = {
                        Text(text = "Sike")
                    },
                    onDismissRequest = {
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {inDialog=false}
                        ) {
                            Text("Volver")
                        }
                    }
                )
            }
            Text( text = stringResource(R.string.MyRouts))
            Order()
        }
        // RoutineScroller(name = , viewModel = ) todo
    }

}