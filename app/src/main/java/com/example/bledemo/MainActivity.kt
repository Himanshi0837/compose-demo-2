package com.example.bledemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bledemo.ui.components.AppBottomBar
import com.example.bledemo.ui.navigation.AppNavGraph
import com.example.bledemo.ui.navigation.AppRoutes
import com.example.bledemo.ui.screens.splash.SplashScreen
import com.example.bledemo.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            AppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var isSplashVisible by rememberSaveable { mutableStateOf(true) }

    if (isSplashVisible) {
        SplashScreen(
            onTimeout = {
                isSplashVisible = false
            }
        )
    } else {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: AppRoutes.HOME

        val bottomBarRoutes = listOf(
            AppRoutes.HOME,
            AppRoutes.FAVOURITE,
            AppRoutes.DRAWER
        )
        val shouldShowBottomBar = currentRoute in bottomBarRoutes

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0),
            containerColor = androidx.compose.ui.graphics.Color(0xFFF3F7FA),
            bottomBar = {
                if (shouldShowBottomBar) {
                    AppBottomBar(
                        currentRoute = currentRoute,
                        onNavigateToRoute = { targetRoute ->
                            navController.navigate(targetRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                startDestination = AppRoutes.HOME,
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
            )
        }
    }
}
