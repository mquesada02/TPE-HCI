package ar.edu.itba.hci.fiit_mobile.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Components.RountineInfo

@Composable
@Preview(showBackground = true, name = "Testing")
fun RoutineScreen(){
     val routineName = "name"//de la Api todo

    Column(){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text =  routineName, //routineName,
                color = MaterialTheme.colorScheme.primary)
            ElevatedButton(onClick = { /* poner lo q esta haciendo manu todo */ }){
                Text(AnnotatedString(text = stringResource(R.string.start)))
            }
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.fiit_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(width = 100.dp, height = 100.dp),
            )
            RountineInfo()
        }
        Row(){
            //Entrada en calor
        }
        Row(){
            //Ejercitación
        }
        Row(){
            //Enfriamiento
        }
    }
}