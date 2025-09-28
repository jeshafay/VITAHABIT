package com.example.vitahabit.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun SettingsScreen(
    onProfileClick: () -> Unit,
    onNotificationsClick: () -> Unit
    // Add more lambdas for other settings pages
) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, start = 28.dp, end = 28.dp, bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 0.dp, horizontal = 0.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Each row in the settings menu is a clickable item
//            SettingsItem(text = "My Profile", icon = painterResource(R.drawable.icon_profile), onClick = onProfileClick)
            SettingsItem(text = "My Profile", icon = Icons.Outlined.Person, onClick = onProfileClick)
            // This is how you would call it with your custom drawable resource
            // SettingsItem(text = "My Profile (Drawable)", icon = painterResource(id = R.drawable.icon_profile), onClick = onProfileClick)
            SettingsItem(text = "Unit of measurements", icon = Icons.Outlined.Person, onClick = {  })
            SettingsItem(text = "Smart Weight & Reps", icon = Icons.Outlined.Person, onClick = {  })
            SettingsItem(text = "Sound", icon = Icons.Outlined.Person, onClick = {  })
            SettingsItem(text = "Reminders", icon = Icons.Outlined.Notifications, onClick = onNotificationsClick)
            SettingsItem(text = "Workout Tab Display", icon = Icons.Outlined.List, onClick = {  })
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 28.dp, end = 28.dp),
//        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    VitaHabitTheme {
        SettingsScreen(onProfileClick = {}, onNotificationsClick = {})
    }
}