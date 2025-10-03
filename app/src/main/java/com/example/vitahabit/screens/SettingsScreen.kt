package com.example.vitahabit.screens

import android.widget.Space
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.ui.theme.VitaHabitTheme

private object SettingsRoutes {
    const val MENU = "settings_menu"
    const val PROFILE = "settings_profile"
    const val UNITS = "settings_units"
    const val SMART_FEATURES = "settings_smart_features"
    const val SOUND = "settings_sound"
    const val REMINDERS = "settings_reminders"
    const val DISPLAY = "settings_display"
}

@Composable
fun SettingsScreen(
    onProfileClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        startDestination = SettingsRoutes.MENU)
    {
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
            SettingsTopBar()
            Spacer(modifier = Modifier.height(10.dp))

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
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture Placeholder
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(72.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Joel Nathan", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                TextButton(onClick = { /* TODO: Handle photo editing */ }) {
                    Text("Edit Photo", color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ProfileDetailRow(label = "Gender", value = "Male")
                        ProfileDetailRow(label = "Age", value = "30")
                        ProfileDetailRow(label = "Weight", value = "75.0 kg")
                        ProfileDetailRow(label = "Height", value = "175.0 cm")
                    }
                }
            }
        }
    }
}

@Composable
private fun UnitsSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "UNIT OF MEASUREMENTS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(label = "Weight Unit", options = listOf("kg", "lbs"))
                RadioButtonSettingItem(label = "Distance Unit", options = listOf("km", "miles"))
            }

        }
    }
}

@Composable
private fun SmartFeaturesSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "SMART WEIGHT & REPS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(label = "Auto-Increment Weight", options = listOf("On", "Off"))
                }
        }
    }
}

@Composable
private fun SoundSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "SOUNDS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(
                    label = "Sound Before Step",
                    options = listOf("5 sec", "0 sec", "off"),
                    initialSelection = 1
                )
                RadioButtonSettingItem(
                    label = "Vibration Before Step",
                    options = listOf("5 sec", "0 sec", "off"),
                    initialSelection = 1
                )
            }
        }
    }
}

@Composable
private fun RemindersSettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "REMINDERS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(
                    label = "Workout Reminders",
                    options = listOf("Daily", "Weekly", "Off")
                )
            }
        }
    }
}

@Composable
private fun DisplaySettingsPage(onNavigateBack: () -> Unit) {
    SettingsPageScaffold(title = "WORKOUT TAB DISPLAY", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(label = "Default View", options = listOf("List", "Grid"))
            }
        }
    }
}

@Composable
private fun RadioButtonSettingItem(label: String, options: List<String>, initialSelection: Int = 0) {
    var selectedOption by remember { mutableStateOf(options[initialSelection]) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                options.forEach { optionText ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        // Make the whole column clickable to select the radio button
                        modifier = Modifier.selectable(
                            selected = (optionText == selectedOption),
                            onClick = { selectedOption = optionText },
                            role = Role.RadioButton
                        )
                    ) {
                        RadioButton(
                            selected = (optionText == selectedOption),
                            onClick = null, // onClick is handled by the parent modifier
                            colors = RadioButtonDefaults.colors(
                                // This makes the selected radio button use your primary (orange) color
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Text(text = optionText, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun ChoiceSettingItem(label: String, options: List<String>, initialSelection: Int = 0) {
//    var selectedIndex by remember { mutableStateOf(initialSelection) }
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
//    ) {
//        Row(
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = label, modifier = Modifier.weight(1f))
//            SingleChoiceSegmentedButtonRow {
//                options.forEachIndexed { index, optionLabel ->
//                    SegmentedButton(
//                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
//                        onClick = { selectedIndex = index },
//                        selected = index == selectedIndex
//                    ) {
//                        Text(optionLabel)
//                    }
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsPageScaffold(
    title: String,
    onNavigateBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = title) },
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
fun SettingsTopBar() {
    Spacer(modifier = Modifier.height(2.dp))
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    }
}


@Composable
private fun SettingsItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun ProfileDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
        )
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