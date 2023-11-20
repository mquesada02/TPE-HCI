package ar.edu.itba.hci.fiit_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.hci.fiit_mobile.ui.views.FavsScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.HomeScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RoutinesScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.SearcherScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ConfirmEmailScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.LoginScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RegisterScreen

@Composable
fun FIITNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.LoginScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(onNavigateToScreen = {s -> navController.popBackStack(Screen.RegisterScreen.route,true); navController.navigate(s) {
                popUpTo(Screen.LoginScreen.route) { inclusive = true }
            }  })
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(onNavigateToConfirmEmailScreen = { navController.navigate(Screen.ConfirmEmailScreen.route) })
        }
        composable(Screen.ConfirmEmailScreen.route) {
            ConfirmEmailScreen(onNavigateToLoginScreen = { navController.navigate(Screen.LoginScreen.route) })
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
        composable(Screen.FavsScreen.route){
            FavsScreen(onNavigateToScreen = {s -> navController.navigate(s)})
        }
        composable(Screen.SearcherScreen.route){
            SearcherScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
        composable(Screen.RoutinesScreen.route){
            RoutinesScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
//        composable("routine/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType})) {
//            route -> RoutineScreen(route.arguments?.getInt("id"))
//        }
    }
}