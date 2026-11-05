package com.example.bledemo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.BluetoothSearching
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.DensityMedium
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bledemo.ui.navigation.AppRoutes
import com.example.bledemo.ui.theme.AccentBlue
import com.example.bledemo.ui.theme.CardBackground
import com.example.bledemo.ui.theme.PrimaryBlue
import com.example.bledemo.ui.theme.SecondaryText

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = AppRoutes.HOME
        ),
        BottomNavItem(
            title = "Favourite",
            icon = Icons.Default.FavoriteBorder,
            route = AppRoutes.SCAN
        ),

        BottomNavItem(
            title = "Drawer",
            icon = Icons.Default.Dehaze,
            route = AppRoutes.SETTINGS
        )
    )

    NavigationBar(
        modifier = modifier,
        containerColor = CardBackground,
        contentColor = PrimaryBlue
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onNavigateToRoute(item.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    indicatorColor = PrimaryBlue.copy(alpha = 0.12f),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )
        }
    }
}
