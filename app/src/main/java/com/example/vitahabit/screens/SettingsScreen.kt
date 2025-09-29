package com.example.vitahabit.screens

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.ui.theme.VitaHabitTheme

// --- 1. Define Routes for Internal Navigation ---
private object SettingsRoutes {
    const val MENU = "settings_menu"
    const val PROFILE = "settings_profile"
    const val UNITS = "settings_units"
    const val SMART_FEATURES = "settings_smart_features"
    const val SOUND = "settings_sound"
    const val REMINDERS = "settings_reminders"
    const val DISPLAY = "settings_display"
}

// --- 2. Main Entry Point for Settings Navigation ---
@Composable
fun SettingsScreen(
    onProfileClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SettingsRoutes.MENU) {
        composable(SettingsRoutes.MENU) {
            SettingsMenuScreen(
                onProfileClick = { navController.navigate(SettingsRoutes.PROFILE) },
                onUnitsClick = { navController.navigate(SettingsRoutes.UNITS) },
                onSmartFeaturesClick = { navController.navigate(SettingsRoutes.SMART_FEATURES) },
                onSoundClick = { navController.navigate(SettingsRoutes.SOUND) },
                onRemindersClick = { navController.navigate(SettingsRoutes.REMINDERS) },
                onDisplayClick = { navController.navigate(SettingsRoutes.DISPLAY) }
            )
        }
        composable(SettingsRoutes.PROFILE) { ProfileSettingsPage(onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.UNITS) { UnitsSettingsPage(onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.SMART_FEATURES) { SmartFeaturesSettingsPage(onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.SOUND) { SoundSettingsPage(onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.REMINDERS) { RemindersSettingsPage(onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.DISPLAY) { DisplaySettingsPage(onNavigateBack = { navController.popBackStack() }) }
    }
}

// --- 3. The Main Settings Menu UI ---
@Composable
private fun SettingsMenuScreen(
    onProfileClick: () -> Unit,
    onUnitsClick: () -> Unit,
    onSmartFeaturesClick: () -> Unit,
    onSoundClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onDisplayClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, start = 28.dp, end = 28.dp, bottom = 8.dp)
            )
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SettingsItem(text = "My Profile", icon = Icons.Outlined.Person, onClick = onProfileClick)
                SettingsItem(text = "Unit of measurements", icon = Icons.Outlined.Person, onClick = onUnitsClick)
                SettingsItem(text = "Smart Weight & Reps", icon = Icons.Outlined.Person, onClick = onSmartFeaturesClick)
                SettingsItem(text = "Sound", icon = Icons.Outlined.Person, onClick = onSoundClick)
                SettingsItem(text = "Reminders", icon = Icons.Outlined.Notifications, onClick = onRemindersClick)
                SettingsItem(text = "Workout Tab Display", icon = Icons.AutoMirrored.Outlined.List, onClick = onDisplayClick)
            }
        }
    }
}

@Composable
private fun ProfileSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "MY PROFILE", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Edit Name", options = listOf("Option 1", "Option 2"))
            Spacer(modifier = Modifier.height(4.dp))
            ChoiceSettingItem(label = "Change Password", options = listOf("Yes", "No"))
        }
    }
}

@Composable
private fun UnitsSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "UNIT OF MEASUREMENTS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Weight Unit", options = listOf("kg", "lbs"))
            HorizontalDivider()
            ChoiceSettingItem(label = "Distance Unit", options = listOf("km", "miles"))
        }
    }
}

@Composable
private fun SmartFeaturesSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "SMART WEIGHT & REPS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Auto-Increment Weight", options = listOf("On", "Off"))
        }
    }
}

@Composable
private fun SoundSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "SOUNDS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Sound Before Step", options = listOf("5 sec", "0 sec", "off"), initialSelection = 1)
            HorizontalDivider()
            ChoiceSettingItem(label = "Vibration Before Step", options = listOf("5 sec", "0 sec", "off"), initialSelection = 1)
        }
    }
}

@Composable
private fun RemindersSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "REMINDERS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Workout Reminders", options = listOf("Daily", "Weekly", "Off"))
        }
    }
}

@Composable
private fun DisplaySettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "WORKOUT TAB DISPLAY", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChoiceSettingItem(label = "Default View", options = listOf("List", "Grid"))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChoiceSettingItem(label: String, options: List<String>, initialSelection: Int = 0) {
    var selectedIndex by remember { mutableStateOf(initialSelection) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, modifier = Modifier.weight(1f))
            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, optionLabel ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex
                    ) {
                        Text(optionLabel)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsPageScaffold(
    title: String,
    onNavigateBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) }, // Removed style for default TopAppBar styling
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        content = content
    )
}

@Composable
private fun SettingsItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    VitaHabitTheme {
        // Previewing the main menu is the most straightforward
        SettingsMenuScreen({}, {}, {}, {}, {}, {})
    }
}