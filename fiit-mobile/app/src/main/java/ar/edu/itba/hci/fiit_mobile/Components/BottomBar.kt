package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.edu.itba.hci.fiit_mobile.R
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo


@Composable
fun BottomBar(navController: NavController){

    var inHome by remember { mutableStateOf(true) }
    var inSearcher by remember { mutableStateOf(false) }
    var inFavs by remember { mutableStateOf(false) }
    var inMyRoutines by remember { mutableStateOf(false) }

    val windowInfo = rememberWindowInfo()

    inHome = navController.currentDestination?.route == Screen.HomeScreen.route
    inSearcher = navController.currentDestination?.route == Screen.SearchScreen.route
    inFavs = navController.currentDestination?.route == Screen.FavsScreen.route
    inMyRoutines = navController.currentDestination?.route == Screen.RoutinesScreen.route
    BottomAppBar(
                containerColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.height( if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact) 30.dp else 80.dp),
                actions = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack(Screen.HomeScreen.route, false)
                            navController.navigate(Screen.HomeScreen.route)
                        }){
                            if(inHome) {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Home",
                                    modifier = Modifier.size(30.dp),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                            else{
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Home",
                                    modifier = Modifier.size(20.dp).alpha(0.45f),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                        Text(text = stringResource(R.string.home))
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack(Screen.HomeScreen.route, false)
                            navController.navigate(Screen.FavsScreen.route)
                        }) {
                            if(inFavs){
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(30.dp),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                            else{
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(20.dp).alpha(0.45f),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                        Text(text = stringResource(R.string.Favs))
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack(Screen.HomeScreen.route, false)
                            navController.navigate(Screen.RoutinesScreen.route)
                        }) {
                            if(inMyRoutines){
                                Icon(
                                    Icons.Filled.Folder,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(30.dp),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                            else{
                                Icon(
                                    Icons.Filled.Folder,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(20.dp).alpha(0.45f),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                        Text(text = stringResource(R.string.MyRouts))
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack(Screen.HomeScreen.route, false)
                            navController.navigate(Screen.SearchScreen.route)
                        }) {
                            if(inSearcher){
                                Icon(
                                    Icons.Filled.Search,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(30.dp),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                            else{
                                Icon(
                                    Icons.Filled.Search,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(20.dp).alpha(0.45f),
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                        Text(text = stringResource(R.string.search_button))
                    }
                }
                },
            )

}
