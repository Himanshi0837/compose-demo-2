package com.example.bledemo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.bledemo.ui.theme.ErrorRed
import com.example.bledemo.ui.theme.SecondaryText
import com.example.bledemo.ui.theme.SuccessGreen

@Composable
fun SignalIndicator(
    rssi: Int,
    modifier: Modifier = Modifier
) {
    val signalColor = when {
        rssi >= -55 -> SuccessGreen
        rssi >= -70 -> Color(0xFFFFA000)
        else -> ErrorRed
    }

    Column(modifier = modifier) {
        Text(
            text = "$rssi dBm",
            color = signalColor,
            fontSize = 12.sp
        )

        Text(
            text = when {
                rssi >= -55 -> "Excellent"
                rssi >= -70 -> "Good"
                else -> "Weak"
            },
            color = SecondaryText,
            fontSize = 10.sp
        )
    }
}
