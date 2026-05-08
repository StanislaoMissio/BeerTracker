package com.beertracker.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beertracker.android.ui.screens.*
import com.beertracker.android.viewmodel.AuthViewModel
import com.beertracker.android.viewmodel.DriverViewModel
import com.beertracker.android.viewmodel.StyleViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.uiState.collectAsState()

    val styleViewModel: StyleViewModel = viewModel()
    val styleListState by styleViewModel.listState.collectAsState()
    val styleDetailState by styleViewModel.detailState.collectAsState()

    val driverViewModel: DriverViewModel = viewModel()
    val driverState by driverViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                isLoading = authState.isLoading,
                error = authState.error,
                onLogin = { email, password ->
                    authViewModel.login(email, password)
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onClearError = { authViewModel.resetState() }
            )

            LaunchedEffect(authState.isLoggedIn) {
                if (authState.isLoggedIn) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                isLoading = authState.isLoading,
                error = authState.error,
                onRegister = { nome, password, email ->
                    authViewModel.register(nome, password, email)
                },
                onBack = { navController.popBackStack() },
                onClearError = { authViewModel.resetState() }
            )

            LaunchedEffect(authState.isLoggedIn) {
                if (authState.isLoggedIn) {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            }
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToStyles = {
                    navController.navigate(Screen.StyleList.route)
                },
                onNavigateToDriver = {
                    navController.navigate(Screen.Driver.route)
                }
            )
        }

        composable(Screen.StyleList.route) {
            LaunchedEffect(Unit) {
                styleViewModel.loadStyles()
            }

            StyleListScreen(
                styles = styleListState.styles,
                isLoading = styleListState.isLoading,
                error = styleListState.error,
                onStyleClick = { index ->
                    navController.navigate(Screen.StyleDetail.createRoute(index))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.StyleDetail.route,
            arguments = listOf(navArgument("styleIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val styleIndex = backStackEntry.arguments?.getInt("styleIndex") ?: 0
            val style = styleViewModel.getStyleAt(styleIndex)

            LaunchedEffect(style) {
                if (style != null) {
                    styleViewModel.loadHarmonies(style)
                }
            }

            StyleDetailScreen(
                style = style,
                harmonies = styleDetailState.harmonies,
                isLoading = styleDetailState.isLoading,
                error = styleDetailState.error,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Driver.route) {
            DriverScreen(
                drivers = driverState.drivers,
                onAddDriver = { driverViewModel.addDriver(it) },
                onRemoveDriver = { driverViewModel.removeDriver(it) },
                onPickDriver = { driverViewModel.pickRandomDriver() },
                onNavigateToChosen = { name ->
                    navController.navigate(Screen.ChosenDriver.createRoute(name))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.ChosenDriver.route,
            arguments = listOf(navArgument("driverName") { type = NavType.StringType })
        ) { backStackEntry ->
            val driverName = backStackEntry.arguments?.getString("driverName") ?: ""

            ChosenDriverScreen(
                driverName = driverName,
                onPickAgain = { navController.popBackStack() },
                onGoHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
