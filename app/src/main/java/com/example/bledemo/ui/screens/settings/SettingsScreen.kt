package com.example.bledemo.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bledemo.ui.components.AppTopBar
import com.example.bledemo.ui.components.StatusBadge
import com.example.bledemo.ui.theme.AccentBlue
import com.example.bledemo.ui.theme.BackgroundColor
import com.example.bledemo.ui.theme.CardBackground
import com.example.bledemo.ui.theme.CardShape
import com.example.bledemo.ui.theme.PrimaryText
import com.example.bledemo.ui.theme.SecondaryText

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    var autoConnectEnabled by remember { mutableStateOf(true) }
    var filterUnnamedDevices by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(title = "Settings")
        },
        containerColor = BackgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Text(
                text = "Bluetooth Preferences",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = CardShape,
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Auto-reconnect",
                                style = MaterialTheme.typography.titleSmall,
                                color = PrimaryText
                            )
                            Text(
                                text = "Automatically reconnect to known devices",
                                style = MaterialTheme.typography.bodySmall,
                                color = SecondaryText
                            )
                        }
                        Switch(
                            checked = autoConnectEnabled,
                            onCheckedChange = { autoConnectEnabled = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = AccentBlue)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Filter Unnamed Devices",
                                style = MaterialTheme.typography.titleSmall,
                                color = PrimaryText
                            )
                            Text(
                                text = "Hide devices without advertised local name",
                                style = MaterialTheme.typography.bodySmall,
                                color = SecondaryText
                            )
                        }
                        Switch(
                            checked = filterUnnamedDevices,
                            onCheckedChange = { filterUnnamedDevices = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = AccentBlue)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Permissions & App Info",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = CardShape,
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = AccentBlue
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "BLE Permissions",
                            style = MaterialTheme.typography.titleSmall,
                            color = PrimaryText,
                            modifier = Modifier.weight(1f)
                        )
                        StatusBadge(text = "Phase 4")
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = SecondaryText
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "BLE Demo v1.0",
                                style = MaterialTheme.typography.titleSmall,
                                color = PrimaryText
                            )
                            Text(
                                text = "Phase 1: Foundation & Navigation",
                                style = MaterialTheme.typography.bodySmall,
                                color = SecondaryText
                            )
                        }
                    }
                }
            }
        }
    }
}

