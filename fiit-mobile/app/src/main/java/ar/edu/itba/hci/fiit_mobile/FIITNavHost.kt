package ar.edu.itba.hci.fiit_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.hci.fiit_mobile.ui.ConfirmEmailScreen
import ar.edu.itba.hci.fiit_mobile.ui.LoginScreen
import ar.edu.itba.hci.fiit_mobile.ui.RegisterScreen

@Composable
fun FIITNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(onNavigateToConfirmEmailScreen = { navController.navigate(Screen.ConfirmEmailScreen.route) })
        }
        composable(Screen.ConfirmEmailScreen.route) {
            ConfirmEmailScreen(onNavigateToLoginScreen = { navController.navigate(Screen.LoginScreen.route) })
        }
//        composable("routine/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType})) {
//            route -> RoutineScreen(route.arguments?.getInt("id"))
//        }
    }
}