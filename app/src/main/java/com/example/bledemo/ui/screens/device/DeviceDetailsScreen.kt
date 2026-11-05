package com.example.bledemo.ui.screens.device

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bledemo.ui.components.AppTopBar
import com.example.bledemo.ui.theme.BackgroundColor

@Composable
fun DeviceDetailsScreen(
    deviceAddress: String,
    onNavigateBack: () -> Unit,
    onOpenGatt: (String) -> Unit,
    onOpenLiveData: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                title = "Device Details",
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Device: $deviceAddress")
        }
    }
}
