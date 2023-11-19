package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.data.network.model.NetworkRoutineContent
import retrofit2.Response

@Composable
fun RountineInfo(data: NetworkRoutineContent){

    val intensityType = data.difficulty
    val score = data.score
    var isFav by remember { mutableStateOf(false) } //donde esta info de favs en la api ?? todo
    val icon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Column {
        Row(){
            //Puntuaje en estrellitas q actualiza al momento la variable score todo
            Text( text = score.toString())
        }
        Row(){
            Text(text =  stringResource(R.string.intensity),
                modifier = Modifier.padding(end = 5.dp))
            Text(text = intensityType)
        }
        Row(){
            IconButton(onClick = { /* tiene q copiar el link todo */ }) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(20.dp).shadow(10.dp)
                )
            }
            IconButton(onClick = {
                if (isFav) {
                    //todo
                } else {
                }
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isFav) MaterialTheme.colorScheme.primary
                           else MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(10.dp)
                )
            }
        }
    }
}