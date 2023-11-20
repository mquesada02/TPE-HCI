package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.Components.BottomBar
import ar.edu.itba.hci.fiit_mobile.Components.MenuCard
import ar.edu.itba.hci.fiit_mobile.Components.TopAppBar
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.ui.states.canGetAllRoutines
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.LoginViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun HomeScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())) {

    val username = viewModel.getUsername() //esto esta mal, devuelve job, no string todo

   Column(
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Row(
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = stringResource(R.string.welcome).plus(username),
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
       ) {
           FiitmobileTheme {
               MenuCard(name = stringResource(R.string.SearchR),
                   iconType = Icons.Filled.Search,
                   onClick = { onNavigateToScreen(Screen.SearcherScreen.route) })
           }

           FiitmobileTheme {
               MenuCard(name = stringResource(R.string.CreateR),
                   iconType = Icons.Filled.Add,
                   onClick = { onNavigateToScreen(Screen.ErrorScreen.route) })
           }
       }
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp),
           horizontalArrangement = Arrangement.SpaceAround
       ) {
           FiitmobileTheme {
               MenuCard(name = stringResource(R.string.MyRouts),
                   iconType = Icons.Filled.MailOutline,
                   onClick = { onNavigateToScreen(Screen.RoutinesScreen.route) })
           }
           FiitmobileTheme {
               MenuCard(name = stringResource(R.string.Favs),
                   iconType = Icons.Filled.Favorite,
                   onClick = { onNavigateToScreen(Screen.FavsScreen.route) })
           }
       }
       Text(text = stringResource(R.string.Featured))
       if(!viewModel.uiState.canGetAllRoutines){
           Card(
               colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
               modifier = Modifier
                   .fillMaxWidth()
                   .height(50.dp)
           ) {
               Text(
                   text = stringResource(R.string.Empty),
                   modifier = Modifier.padding(25.dp),
                   textAlign = TextAlign.Center,
               )
           }
       }
       else{
           //RoutineCarrousel(viewModel.getRoutines()) descomentar cuando este listo todo
       }
   }
}


