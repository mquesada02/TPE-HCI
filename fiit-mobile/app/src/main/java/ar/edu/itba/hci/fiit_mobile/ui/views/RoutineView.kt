package ar.edu.itba.hci.fiit_mobile.ui.views

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
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutineContent
import coil.compose.AsyncImage
import ar.edu.itba.hci.fiit_mobile.Components.RoutineInfo

@Composable
fun RoutineScreen(data : NetworkRoutineContent){

    Column(){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text =  data.name,
                color = MaterialTheme.colorScheme.primary)
            ElevatedButton(onClick = { /* poner lo q esta haciendo manu todo */ }){
                Text(AnnotatedString(text = stringResource(R.string.start)))
            }
        }
        Row {
            AsyncImage(
                model = data.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 100.dp, height = 100.dp),
            )
            RoutineInfo(data = data)
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