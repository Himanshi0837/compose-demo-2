package com.example.bledemo.ui.screens.drawer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bledemo.ui.theme.PrimaryBlue

private val DrawerBackground = Color(0xFFF3F7FA)
private val PrimaryButtonColor = PrimaryBlue
private val DarkTextColor = Color(0xFF101828)
private val IconCircleBg = Color(0xFFF0F6FA)
private val IconTintDark = Color(0xFF1D2939)
private val SignOutRed = Color(0xFFFF5238)
private val SignOutCircleBg = Color(0xFFFFF2EF)
private val DividerColor = Color(0xFFF2F4F7)

@Composable
fun DrawerScreen(
    modifier: Modifier = Modifier,
    userName: String = "Jane Cooper",
    onEditProfileClick: () -> Unit = {},
    onReferFriendClick: () -> Unit = {},
    onRateUsClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    var showSignOutDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DrawerBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        // Profile Avatar & Name Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Circular Avatar
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF26C6DA)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Face,
                    contentDescription = "User Avatar",
                    tint = Color.White,
                    modifier = Modifier.size(68.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // User Name
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Edit Profile Button (Pill shaped)
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(PrimaryButtonColor)
                    .clickable(onClick = onEditProfileClick)
                    .padding(horizontal = 22.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Menu Options Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFEAECF0)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                // 1. Refer a Friend
                DrawerMenuItem(
                    title = "Refer a Friend",
                    icon = Icons.Outlined.Share,
                    iconBgColor = IconCircleBg,
                    iconColor = IconTintDark,
                    textColor = DarkTextColor,
                    onClick = onReferFriendClick
                )

                HorizontalDivider(
                    color = DividerColor,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                // 2. Rate Us
                DrawerMenuItem(
                    title = "Rate Us",
                    icon = Icons.Outlined.StarOutline,
                    iconBgColor = IconCircleBg,
                    iconColor = IconTintDark,
                    textColor = DarkTextColor,
                    onClick = onRateUsClick
                )

                HorizontalDivider(
                    color = DividerColor,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                // 3. Delete Account
                DrawerMenuItem(
                    title = "Delete Account",
                    icon = Icons.Outlined.Delete,
                    iconBgColor = IconCircleBg,
                    iconColor = IconTintDark,
                    textColor = DarkTextColor,
                    onClick = { showDeleteDialog = true }
                )

                HorizontalDivider(
                    color = DividerColor,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                // 4. Sign Out
                DrawerMenuItem(
                    title = "Sign Out",
                    icon = Icons.AutoMirrored.Outlined.Logout,
                    iconBgColor = SignOutCircleBg,
                    iconColor = SignOutRed,
                    textColor = SignOutRed,
                    onClick = { showSignOutDialog = true }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }

    // Sign Out Confirmation Dialog
    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            title = { Text(text = "Sign Out", fontWeight = FontWeight.Bold) },
            text = { Text("Are you sure you want to sign out?") },
            confirmButton = {
                Button(
                    onClick = {
                        showSignOutDialog = false
                        onSignOutClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SignOutRed)
                ) {
                    Text("Sign Out", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSignOutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Delete Account Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Delete Account", fontWeight = FontWeight.Bold) },
            text = { Text("This will permanently delete your account and saved data. This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteAccountClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SignOutRed)
                ) {
                    Text("Delete", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun DrawerMenuItem(
    title: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circle Icon
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title Label
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}
