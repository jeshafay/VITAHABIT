package com.example.vitahabit.screens


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.ui.theme.VitaHabitTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

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
            Spacer(modifier = Modifier.height(14.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SettingsItem(text = "My Profile", icon = Icons.Filled.Person, onClick = onProfileClick)
                SettingsItem(text = "Unit of measurements", icon = Icons.Default.SquareFoot, onClick = onUnitsClick)
                SettingsItem(text = "Smart Weight & Reps", icon = Icons.Default.Lightbulb, onClick = onSmartFeaturesClick)
                SettingsItem(text = "Sound", icon = Icons.Default.VolumeUp, onClick = onSoundClick)
                SettingsItem(text = "Reminders", icon = Icons.Default.Notifications, onClick = onRemindersClick)
//                SettingsItem(text = "Workout Tab Display", icon = Icons.AutoMirrored.Outlined.List, onClick = onDisplayClick)
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
            HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
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
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(72.dp),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Joel Nathan", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onPrimary,)
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
            HorizontalDivider(thickness= 1.dp, color = MaterialTheme.colorScheme.outline)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButtonSettingItem(label = "Weight Unit", options = listOf("kg", "lbs"))
                RadioButtonSettingItem(label = "Muscle Unit", options = listOf("cm", "in"))
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
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("We'll adjust you weight and reps\n based on your progress", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(bottom = 12.dp).align(Alignment.CenterHorizontally),textAlign = TextAlign.Center)
                RadioButtonSettingItem(label = "Smart Weight & Reps", options = listOf("On", "Off"))
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
            HorizontalDivider(thickness= 1.dp, color = MaterialTheme.colorScheme.outline)
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
    SettingsPageScaffold(title = "WORKOUT REMINDERS", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
            Card(
                modifier = Modifier.padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    val days = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
                    days.forEachIndexed { index, day ->
                        ReminderItem(
                            day = day,
                            initialTime = "09:00",
                            isEnabled = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ReminderItem(
    day: String,
    initialTime: String,
    isEnabled: Boolean
) {
    var checked by remember { mutableStateOf(isEnabled) }
    var showTimePicker by remember { mutableStateOf(false) }
    // Keep the internal state in 24-hour format for consistency
    var selectedTime24 by remember { mutableStateOf(initialTime) }

    if (showTimePicker) {
        TimePickerDialog(
            initialTime = selectedTime24,
            onDismiss = { showTimePicker = false },
            onConfirm = { newHour, newMinute ->
                // The dialog confirms with 24-hour time
                selectedTime24 = String.format("%02d:%02d", newHour, newMinute)
                showTimePicker = false
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.surface,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.background,
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent
            )
        )
        Text(
            text = day,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.background,
            onClick = { showTimePicker = true },
        ) {
            Text(
                text = formatTime(selectedTime24),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(
                    alpha = if (checked) 1f else 0.5f
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

private fun formatTime(time24: String): String {
    // Input format is 24-hour (e.g., "HH:mm")
    val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    // Output format is 12-hour with AM/PM (e.g., "hh:mm a")
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return try {
        val date = inputFormat.parse(time24)
        outputFormat.format(date)
    } catch (e: Exception) {
        time24
    }
}

@Composable
private fun TimePickerDialog(
    initialTime: String,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit
) {
    val (initialHour, initialMinute) = remember {
        initialTime.split(":").map { it.toInt() }
    }

    val hours = (1..12).map { it.toString().padStart(2, '0') }
    val minutes = (0..59).map { it.toString().padStart(2, '0') }
    val amPm = listOf("AM", "PM")

    // The 'middle' of the infinite list, aligned to our initial value
    val initialHourIndex = (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % hours.size) + (initialHour -1)
    val initialMinuteIndex = (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % minutes.size) + initialMinute

    val hourState = rememberLazyListState(initialFirstVisibleItemIndex = initialHourIndex)
    val minuteState = rememberLazyListState(initialFirstVisibleItemIndex = initialMinuteIndex)
    val amPmState = rememberLazyListState(initialFirstVisibleItemIndex = if (initialHour < 12) 0 else 1)

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val selectedHour24 = (hourState.firstVisibleItemIndex % hours.size) + 1
                val selectedMinute = minuteState.firstVisibleItemIndex % minutes.size
                val selectedAmPm = amPm[amPmState.firstVisibleItemIndex % amPm.size]
                // Convert to 24-hour format for consistency
                val finalHour = if (selectedAmPm == "PM" && selectedHour24 != 12) selectedHour24 + 12 else if (selectedAmPm == "AM" && selectedHour24 == 12) 0 else selectedHour24

                onConfirm(finalHour, selectedMinute)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScrollablePicker(items = hours, state = hourState, modifier = Modifier.weight(1f))
                Text(":", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground)
                ScrollablePicker(items = minutes, state = minuteState, modifier = Modifier.weight(1f))
                ScrollablePicker(items = amPm, state = amPmState, isInfinite = false, modifier = Modifier.weight(1f))
            }
        }
    )
}

@Composable
private fun ScrollablePicker(
    items: List<String>,
    state: LazyListState,
    isInfinite: Boolean = true,
    modifier: Modifier = Modifier
) {
    val itemHeight = 48.dp
    val coroutineScope = rememberCoroutineScope()

    // This derived state helps us know which item is in the middle
    val centerIndex by remember {
        derivedStateOf { state.firstVisibleItemIndex }
    }

    // This effect snaps the list to the center item when scrolling stops
    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) {
            coroutineScope.launch {
                state.animateScrollToItem(centerIndex)
            }
        }
    }

    LazyColumn(
        state = state,
        modifier = modifier.height(itemHeight * 3),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
        // Add padding to the top and bottom to center the selected item
        contentPadding = PaddingValues(vertical = itemHeight)
    ) {
        if (isInfinite) {
            // Infinite (looping) list logic
            items(Int.MAX_VALUE) { index ->
                val isSelected = index == centerIndex
                val itemIndex = index % items.size
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = items[itemIndex],
                        style = if (isSelected) MaterialTheme.typography.displayMedium else MaterialTheme.typography.displaySmall,
                        fontSize = if (isSelected) 28.sp else 19.sp,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        } else {
            // Finite (non-looping) list logic
            itemsIndexed(items) { index, item ->
                val isSelected = index == centerIndex
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item, // Use 'item' directly
                        style = if (isSelected) MaterialTheme.typography.displayMedium else MaterialTheme.typography.displaySmall,
                        fontSize = if (isSelected) 28.sp else 19.sp,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
                    )
                }
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
            HorizontalDivider(thickness= 1.dp, color = MaterialTheme.colorScheme.outline)
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
            Text(text = label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.weight(1f))
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
                    title = { Text(text = title, color = MaterialTheme.colorScheme.onPrimary) },
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
                    ),
                    windowInsets = TopAppBarDefaults.windowInsets
                        .exclude(WindowInsets.statusBars)
                        .add(WindowInsets(top = 3.dp))
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
        HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)

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