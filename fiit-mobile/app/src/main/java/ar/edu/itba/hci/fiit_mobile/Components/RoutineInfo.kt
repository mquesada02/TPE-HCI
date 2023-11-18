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

@Preview(showBackground = true, name = "Testing")
@Composable
fun RountineInfo(){

    val intensityType = "b"         //de la api todo
    val score = 5                   //de la api todo
    var isFilled by remember { mutableStateOf(false) } //de la api todo
    val icon = if (isFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Column {
        Row(){
            //Puntuaje
            Text( text = score.toString())
        }
        Row(){
            Text(text =  stringResource(R.string.intensity),
                modifier = Modifier.padding(end = 5.dp))
            Text(text = score.toString())
        }
        Row(){
            IconButton(onClick = { /* tiene q copiar el link todo */ }) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(20.dp).shadow(10.dp)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isFilled) MaterialTheme.colorScheme.primary
                           else MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(10.dp)
                        .clickable {
                            isFilled = !isFilled //esto en realidad modifica la variable de api todo
                        }
                )
            }
        }
    }
}