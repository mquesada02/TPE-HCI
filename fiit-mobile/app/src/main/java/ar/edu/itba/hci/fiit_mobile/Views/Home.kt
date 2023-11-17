package ar.edu.itba.hci.fiit_mobile.Views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.Components.BottomBar
import ar.edu.itba.hci.fiit_mobile.Components.MenuCard
import ar.edu.itba.hci.fiit_mobile.Components.TopAppBar
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme

@Composable
@Preview(showBackground = true, name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
fun Home(){
   val username = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TopAppBar()  // :( porq rompe todoo aaaaa todo
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.welcome).plus(username.value),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .shadow(5.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            FiitmobileTheme {
                MenuCard(name = "Buscar rutinas", iconType = Icons.Filled.Search)
            }

            FiitmobileTheme {
                MenuCard(name = "Crear rutinas", iconType = Icons.Filled.Add)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            FiitmobileTheme {
                MenuCard(name = "Mis rutinas", iconType = Icons.Filled.MailOutline)
            }
            FiitmobileTheme {
                MenuCard(name = "Favoritas", iconType = Icons.Filled.Favorite)
            }
        }
        //acá iria el carousel todo
        BottomBar()
    }
}


