package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory
import coil.compose.AsyncImage

@Composable
fun RoutineCard(data : NetworkRoutineContent, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())){

    val navController = rememberNavController()

    Card(
        modifier = Modifier
            .clickable(onClick = {
                viewModel.uiState = viewModel.uiState.copy(currentRoutine = data)
                navController.navigate(Screen.RoutinesScreen.route)
            })
            .width(100.dp)
            .height(100.dp)
            .background(MaterialTheme.colorScheme.background),

    ){
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            AsyncImage(
                model = data.metadata.img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth() //para  forma de circulo se puede agregar un size fijo y .clip(CircleShape)
                    .height(40.dp)
            )
            Text(text = data.name, fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
            Row (
                modifier = Modifier
                    .padding(bottom = 3.dp)
                    .padding(start = 5.dp)
                    .padding(end = 5.dp)
                    .weight(0.15f),
                verticalAlignment = Alignment.Bottom
                ){
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                    Text(
                        text = data.user.username,
                        fontSize = 9.sp
                    )
                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.width(20.dp)) {
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Star, contentDescription = "", modifier = Modifier.size(12.dp))
                        Text(
                            text = "${data.score}",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}