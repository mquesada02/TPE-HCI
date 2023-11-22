package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ar.edu.itba.hci.fiit_mobile.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController){
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {}, //no se necesita, pero no se puede eliminar todo
                navigationIcon = { if (navController.currentDestination?.route != Screen.HomeScreen.route)
                    IconButton(onClick = { navController.popBackStack().not() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack(Screen.HomeScreen.route, false);navController.navigate(Screen.ProfileScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
}


