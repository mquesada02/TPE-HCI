package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.Components.MenuCard
import ar.edu.itba.hci.fiit_mobile.Components.RoutineCarrousel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.ui.theme.FiitmobileTheme
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun HomeScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())) {

    var fetchedUser by remember { mutableStateOf(false) }

    if(!fetchedUser) {
        viewModel.getCurrentUser()
        fetchedUser = true
    }

    val uiState = viewModel.uiState


   Column(
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Row(
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = (stringResource(R.string.welcome) + " " + uiState.currentUser?.username),
               fontWeight = FontWeight.Bold,
               color = MaterialTheme.colorScheme.primary,
               fontSize = MaterialTheme.typography.titleLarge.fontSize,
           )
       }
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp),
           horizontalArrangement = Arrangement.SpaceAround
       ) {
           FiitmobileTheme {
               MenuCard(
                   name = stringResource(R.string.SearchR),
                   iconType = Icons.Filled.Search
               ) { onNavigateToScreen(Screen.SearchScreen.route) }
           }

           FiitmobileTheme {
               MenuCard(
                   name = stringResource(R.string.CreateR),
                   iconType = Icons.Filled.Add
               ) { onNavigateToScreen(Screen.ErrorScreen.route) }
           }
       }
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp),
           horizontalArrangement = Arrangement.SpaceAround
       ) {
           FiitmobileTheme {
               MenuCard(
                   name = stringResource(R.string.MyRouts),
                       iconType = Icons.Filled.Folder
               )  { onNavigateToScreen(Screen.RoutinesScreen.route) }


           }
           FiitmobileTheme {
               MenuCard(
                   name = stringResource(R.string.Favs),
                       iconType = Icons.Filled.Favorite
               ) { onNavigateToScreen(Screen.FavsScreen.route) }
           }
       }
       RoutineCarrousel(stringResource(R.string.Featured), viewModel.uiState.routines)
   }
}


@Preview
@Composable
fun homePrev(){
    val navController = rememberNavController()

    HomeScreen(onNavigateToScreen = {s -> navController.navigate(s)} )
}

