package ar.edu.itba.hci.fiit_mobile

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ar.edu.itba.hci.fiit_mobile.ui.viewmodels.LoginViewModel
import ar.edu.itba.hci.fiit_mobile.ui.views.FavsScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.HomeScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RoutinesScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.SearchScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ConfirmEmailScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ExecuteRoutineScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.LoginScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.ProfileScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RegisterScreen
import ar.edu.itba.hci.fiit_mobile.ui.views.RoutineScreen
import ar.edu.itba.hci.fiit_mobile.util.getViewModelFactory

@Composable
fun FIITNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.LoginScreen.route
) {
    val auth = viewModel<LoginViewModel>(factory = getViewModelFactory()).uiState.isAuthenticated
    NavHost(
        navController = navController,
        startDestination = if(!auth) startDestination else Screen.HomeScreen.route
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
            SearchScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
        composable(Screen.RoutinesScreen.route){
            RoutinesScreen(onNavigateToScreen = {s -> navController.navigate(s) })
        }
        composable(Screen.RoutineScreen.route, arguments = listOf(navArgument("id") { type = NavType.IntType}),
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://www.fiit.com/routine/{id}"
                action = Intent.ACTION_VIEW
            })
        ) {
            route -> RoutineScreen(onNavigateToScreen = {s -> navController.navigate(s)}, routineId = route.arguments?.getInt("id") ?: 0)
        }
        composable(Screen.ExecuteRoutineScreen.route, arguments = listOf(navArgument("id") { type = NavType.IntType})) {
            route -> ExecuteRoutineScreen(route.arguments?.getInt("id") ?: 0)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) })
        }
//        composable("routine/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType})) {
//            route -> RoutineScreen(route.arguments?.getInt("id"))
//        }
    }
}