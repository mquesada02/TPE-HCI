package ar.edu.itba.hci.fiit_mobile.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R
import coil.compose.AsyncImage
import ar.edu.itba.hci.fiit_mobile.Components.RountineInfo
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import retrofit2.Response

@Composable
fun RoutineScreen(data: NetworkRoutineContent){

    Column(){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text =  data.name, //routineName,
                color = MaterialTheme.colorScheme.primary)
            ElevatedButton(onClick = { /* poner lo q esta haciendo manu todo */ }){
                Text(AnnotatedString(text = stringResource(R.string.start)))
            }
        }
        Row {
            AsyncImage(
                model = data.metadata.img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 100.dp, height = 100.dp),
            )
           // RoutineInfo()  todo
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