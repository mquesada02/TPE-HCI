package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.edu.itba.hci.fiit_mobile.Screen
import ar.edu.itba.hci.fiit_mobile.WindowInfo
import ar.edu.itba.hci.fiit_mobile.rememberWindowInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController){
    val windowInfo = rememberWindowInfo()

    CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ),
                modifier = Modifier.height( if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact) 30.dp else 80.dp),
                title = {}, //no se necesita, pero no se puede eliminar todo
                navigationIcon = { if (navController.currentDestination?.route != Screen.HomeScreen.route)
                    IconButton(onClick = { navController.popBackStack().not() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(onClick = { navController.popBackStack(Screen.HomeScreen.route, false);navController.navigate(Screen.ProfileScreen.route) }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Localized description",
                                tint = Color.Black
                            )
                        }
                    }

                }
            )
}


