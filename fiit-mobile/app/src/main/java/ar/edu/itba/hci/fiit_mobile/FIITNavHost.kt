package ar.edu.itba.hci.fiit_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.itba.hci.fiit_mobile.data.network.model.routines.NetworkRoutineContent
import ar.edu.itba.hci.fiit_mobile.ui.views.FavsScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.HomeScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RoutinesScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.SearchScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ConfirmEmailScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ExecuteRoutineScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.LoginScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RegisterScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RoutineScreen

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
        composable(Screen.SearchScreen.route){
            SearchScreen()
        }
        composable(Screen.RoutinesScreen.route){
            RoutinesScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }

        composable(Screen.ExecuteRoutineScreen.route) {
            ExecuteRoutineScreen(14)
        }
//        composable("routine/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType})) {
//            route -> RoutineScreen(route.arguments?.getInt("id"))
//        }
    }
}