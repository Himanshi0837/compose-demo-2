package com.example.bledemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bledemo.ui.screens.device.DeviceDetailsScreen
import com.example.bledemo.ui.screens.device.GattScreen
import com.example.bledemo.ui.screens.home.HomeScreen
import com.example.bledemo.ui.screens.live_data.LiveDataScreen
import com.example.bledemo.ui.screens.my_devices.MyDevicesScreen
import com.example.bledemo.ui.screens.scan.ScanScreen
import com.example.bledemo.ui.screens.settings.SettingsScreen

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

        composable(AppRoutes.SCAN) {
            ScanScreen(
                onDeviceClick = { macAddress ->
                    navController.navigate(AppRoutes.deviceDetails(macAddress)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.MY_DEVICES) {
            MyDevicesScreen(
                onDeviceClick = { macAddress ->
                    navController.navigate(AppRoutes.deviceDetails(macAddress)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.LIVE_DATA) {
            LiveDataScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.SETTINGS) {
            SettingsScreen()
        }

        composable(
            route = AppRoutes.DEVICE_DETAILS,
            arguments = listOf(
                navArgument("deviceAddress") {
                    type = NavType.StringType
                    defaultValue = "Unknown"
                }
            )
        ) { backStackEntry ->
            val deviceAddress = backStackEntry.arguments?.getString("deviceAddress") ?: "Unknown"
            DeviceDetailsScreen(
                deviceAddress = deviceAddress,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onOpenGatt = { address ->
                    navController.navigate(AppRoutes.gatt(address)) {
                        launchSingleTop = true
                    }
                },
                onOpenLiveData = {
                    navController.navigate(AppRoutes.LIVE_DATA) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.GATT,
            arguments = listOf(
                navArgument("deviceAddress") {
                    type = NavType.StringType
                    defaultValue = "Unknown"
                }
            )
        ) { backStackEntry ->
            val deviceAddress = backStackEntry.arguments?.getString("deviceAddress") ?: "Unknown"
            GattScreen(
                deviceAddress = deviceAddress,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
