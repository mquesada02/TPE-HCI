package ar.edu.itba.hci.fiit_mobile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object LoginScreen: Screen("Login", Icons.Filled.Person, "login")
    object RegisterScreen: Screen("Register", Icons.Filled.Person, "register")
    object ConfirmEmailScreen: Screen("Confirm Email", Icons.Filled.Email, "confirm_email")
    object HomeScreen: Screen("Home", Icons.Filled.Home, "home")
    object ErrorScreen: Screen("Error404", Icons.Filled.Close, "error")
    object FavsScreen : Screen("Favourites", Icons.Filled.Favorite, "favs")
    object RoutinesScreen : Screen("MyRoutines", Icons.Filled.MailOutline,"routines" )
    object SearcherScreen : Screen("Searcher", Icons.Filled.Search, "seach")
    object ExecuteRoutineScreen : Screen("ExecuteRoutine", Icons.Filled.Accessibility, "execute_routine")
}