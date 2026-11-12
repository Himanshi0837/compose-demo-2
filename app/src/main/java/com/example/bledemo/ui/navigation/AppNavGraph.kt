package com.example.bledemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bledemo.ui.screens.drawer.DrawerScreen
import com.example.bledemo.ui.screens.favourite.FavouriteScreen
import com.example.bledemo.ui.screens.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppRoutes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.FAVOURITE) {
            FavouriteScreen()
        }

        composable(AppRoutes.DRAWER) {
            DrawerScreen()
        }

    }
}
