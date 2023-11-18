package ar.edu.itba.hci.fiit_mobile.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.itba.hci.fiit_mobile.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(onNavigateToScreen: (String) -> Unit){
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(onClick = { onNavigateToScreen(Screen.HomeScreen.route)}) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { onNavigateToScreen(Screen.ErrorScreen.route) }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { onNavigateToScreen(Screen.ErrorScreen.route) }) {
                        Icon(
                            Icons.Filled.MailOutline,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { onNavigateToScreen(Screen.ErrorScreen.route) }) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { onNavigateToScreen(Screen.ErrorScreen.route) }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Localized description",
                        )
                    }
                }
                },
            )
        },
    ) { innerPadding -> ScrollContent(innerPadding) }
}
