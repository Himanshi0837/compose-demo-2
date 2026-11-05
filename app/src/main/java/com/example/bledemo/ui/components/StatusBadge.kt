package com.example.bledemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bledemo.ui.theme.SuccessGreen

@Composable
fun StatusBadge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = SuccessGreen
) {
    Text(
        text = text,
        color = color,
        fontSize = 11.sp,
        modifier = modifier
            .background(
                color = color.copy(alpha = 0.12f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}
