package ar.edu.itba.hci.fiit_mobile.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import ar.edu.itba.hci.fiit_mobile.Components.RoutineCarrousel
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.HomeViewModel
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun HomeScreen(onNavigateToScreen: (String) -> Unit, viewModel: HomeViewModel = viewModel(factory = getViewModelFactory())) {

    var fetchedUser by remember { mutableStateOf(false) }
    var fetchedExecutions by remember { mutableStateOf(false) }

    if(!fetchedUser) {
        viewModel.getCurrentUser()
        fetchedUser = true
    }

    val uiState = viewModel.uiState

    /*if(uiState.currentUser != null && !fetchedExecutions) {
        viewModel.executionsWithRoutines()
        fetchedExecutions = true
    }*/

    if(!fetchedExecutions) {
        viewModel.getCurrentExecutions()
        fetchedExecutions = true
    }


   Column(
       modifier = Modifier
           .fillMaxWidth()
           .padding(top = 16.dp),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Row(
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = (stringResource(R.string.welcome) + " " + uiState.currentUser?.username),
               fontWeight = FontWeight.Bold,
               fontSize = MaterialTheme.typography.titleLarge.fontSize,
           )
       }
       RoutineCarrousel(onNavigateToScreen, stringResource(R.string.recent), uiState.recents)
   }
}


@Preview
@Composable
fun HomePrev(){
    val navController = rememberNavController()

    HomeScreen(onNavigateToScreen = {s -> navController.navigate(s)} )
}

