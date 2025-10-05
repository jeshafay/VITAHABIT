package com.example.vitahabit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.ui.theme.VitaHabitTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class ReminderSetting(
    val day: String,
    val time: String = "09:00", // Stored in 24-hour format "HH:mm"
    val isEnabled: Boolean = false
)

data class SettingsUiState(
    // Profile
    val gender: String = "Male",
    val age: String = "30",
    val weight: String = "75.0",
    val height: String = "175.0",

    // Units
    val weightUnit: String = "kg",
    val muscleUnit: String = "cm",
    val distanceUnit: String = "km",

    // Smart Features
    val smartFeatures: String = "On",

    // Sound
    val soundBeforeStep: String = "0 sec",
    val vibrationBeforeStep: String = "0 sec",

    // Reminders
    val reminders: List<ReminderSetting> = listOf(
        ReminderSetting("Sunday"),
        ReminderSetting("Monday"),
        ReminderSetting("Tuesday"),
        ReminderSetting("Wednesday"),
        ReminderSetting("Thursday"),
        ReminderSetting("Friday"),
        ReminderSetting("Saturday")
    )
)

// <<< CHANGED: Expanded ViewModel with update functions for all settings
class SettingsViewModel : ViewModel() {
    private val _uiState = mutableStateOf(SettingsUiState())
    val uiState: State<SettingsUiState> = _uiState

    // --- Profile Updates ---
    fun updateGender(newGender: String) {
        _uiState.value = _uiState.value.copy(gender = newGender)
    }

    fun updateAge(millis: Long?) {
        millis?.let {
            val today = Calendar.getInstance()
            val birthDate = Calendar.getInstance().apply { timeInMillis = it }
            var calculatedAge = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                calculatedAge--
            }
            _uiState.value = _uiState.value.copy(age = calculatedAge.toString())
        }
    }

    fun updateWeight(newWeight: String) {
        _uiState.value = _uiState.value.copy(weight = newWeight)
    }

    fun updateHeight(newHeight: String) {
        _uiState.value = _uiState.value.copy(height = newHeight)
    }

    // --- Units Updates ---
    fun updateWeightUnit(newUnit: String) {
        _uiState.value = _uiState.value.copy(weightUnit = newUnit)
    }
    fun updateMuscleUnit(newUnit: String) {
        _uiState.value = _uiState.value.copy(muscleUnit = newUnit)
    }
    fun updateDistanceUnit(newUnit: String) {
        _uiState.value = _uiState.value.copy(distanceUnit = newUnit)
    }

    // --- Smart Features Update ---
    fun updateSmartFeatures(newValue: String) {
        _uiState.value = _uiState.value.copy(smartFeatures = newValue)
    }

    // --- Sound Updates ---
    fun updateSoundBeforeStep(newValue: String) {
        _uiState.value = _uiState.value.copy(soundBeforeStep = newValue)
    }
    fun updateVibrationBeforeStep(newValue: String) {
        _uiState.value = _uiState.value.copy(vibrationBeforeStep = newValue)
    }

    // --- Reminder Updates ---
    fun updateReminderEnabled(day: String, isEnabled: Boolean) {
        val updatedReminders = _uiState.value.reminders.map {
            if (it.day == day) it.copy(isEnabled = isEnabled) else it
        }
        _uiState.value = _uiState.value.copy(reminders = updatedReminders)
    }

    fun updateReminderTime(day: String, newTime: String) {
        val updatedReminders = _uiState.value.reminders.map {
            if (it.day == day) it.copy(time = newTime) else it
        }
        _uiState.value = _uiState.value.copy(reminders = updatedReminders)
    }
}


// --- 2. Routes and Main Settings NavHost ---
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

) {
    val navController = rememberNavController()
    val viewModel: SettingsViewModel = viewModel()

    NavHost(navController = navController, startDestination = SettingsRoutes.MENU) {
        composable(SettingsRoutes.MENU) {
            SettingsMenuScreen(
                onProfileClick = { navController.navigate(SettingsRoutes.PROFILE) },
                onUnitsClick = { navController.navigate(SettingsRoutes.UNITS) },
                onSmartFeaturesClick = { navController.navigate(SettingsRoutes.SMART_FEATURES) },
                onSoundClick = { navController.navigate(SettingsRoutes.SOUND) },
                onRemindersClick = { navController.navigate(SettingsRoutes.REMINDERS) },
            )
        }
        // Pass the shared ViewModel to each sub-page that needs it
        composable(SettingsRoutes.PROFILE) { ProfileSettingsPage(viewModel = viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.UNITS) { UnitsSettingsPage(viewModel = viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.SMART_FEATURES) { SmartFeaturesSettingsPage(viewModel = viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.SOUND) { SoundSettingsPage(viewModel = viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable(SettingsRoutes.REMINDERS) { RemindersSettingsPage(viewModel = viewModel, onNavigateBack = { navController.popBackStack() }) }
//        composable(SettingsRoutes.DISPLAY) { DisplaySettingsPage(onNavigateBack = { navController.popBackStack() }) }

    }
}

@Composable
private fun SettingsMenuScreen(
    onProfileClick: () -> Unit,
    onUnitsClick: () -> Unit,
    onSmartFeaturesClick: () -> Unit,
    onSoundClick: () -> Unit,
    onRemindersClick: () -> Unit,
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
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                val itemModifier = Modifier.padding(horizontal = 16.dp)
                SettingsItem(text = "My Profile", icon = Icons.Default.Person, onClick = onProfileClick)
                SettingsItem(text = "Unit of measurements", icon = Icons.Default.SquareFoot, onClick = onUnitsClick)
                SettingsItem(text = "Smart Weight & Reps", icon = Icons.Default.Lightbulb, onClick = onSmartFeaturesClick)
                SettingsItem(text = "Sound", icon = Icons.Default.VolumeUp, onClick = onSoundClick)
                SettingsItem(text = "Reminders", icon = Icons.Default.Notifications, onClick = onRemindersClick)
//                SettingsItem(text = "Workout Tab Display", icon = Icons.AutoMirrored.Filled.List, onClick = onDisplayClick, modifier = itemModifier)
            }
        }
    }
}
private fun formatDate(millis: Long?): String {
    if (millis == null) return ""
    val formatter = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileSettingsPage(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState

    var showGenderPicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showWeightPicker by remember { mutableStateOf(false) }
    var showHeightPicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    if (showGenderPicker) {
        GenderPickerDialog(
            currentGender = uiState.gender,
            onDismiss = { showGenderPicker = false },
            onConfirm = { newGender ->
                viewModel.updateGender(newGender)
                showGenderPicker = false
            }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.updateAge(datePickerState.selectedDateMillis)
                    showDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    headlineContentColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.primary,

                    yearContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedYearContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primary,

                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = Color.Transparent
                ),
                title = {
                    Text(
                        "Select Birthdate",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 12.dp)
                    )
                },
                headline = {
                    val selectedDateText = formatDate(datePickerState.selectedDateMillis)
                    Text(
                        text = selectedDateText,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontSize = 36.sp
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 24.dp, end = 12.dp, bottom = 12.dp)
                    )
                }
            )
        }
    }
    if (showWeightPicker) {
        ValuePickerDialog(
            title = "Enter Weight",
            unit = "kg",
            initialValue = uiState.weight,
            onDismiss = { showWeightPicker = false },
            onConfirm = { newWeight ->
                viewModel.updateWeight(newWeight)
                showWeightPicker = false
            }
        )
    }
    if (showHeightPicker) {
        ValuePickerDialog(
            title = "Enter Height",
            unit = "cm",
            initialValue = uiState.height,
            onDismiss = { showHeightPicker = false },
            onConfirm = { newHeight ->
                viewModel.updateHeight(newHeight)
                showHeightPicker = false
            }
        )
    }

    SettingsPageScaffold(title = "MY PROFILE", onNavigateBack = onNavigateBack) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ProfileDetailRow(label = "Gender", value = uiState.gender, onClick = { showGenderPicker = true })
                        ProfileDetailRow(label = "Age", value = uiState.age, onClick = { showDatePicker = true })
                        ProfileDetailRow(label = "Weight", value = "${uiState.weight} kg", onClick = { showWeightPicker = true })
                        ProfileDetailRow(label = "Height", value = "${uiState.height} cm", onClick = { showHeightPicker = true })
                    }
                }
            }
        }
    }
}
@Composable
private fun UnitsSettingsPage(viewModel: SettingsViewModel, onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState

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
                RadioButtonSettingItem(
                    label = "Weight Unit",
                    options = listOf("kg", "lbs"),
                    selectedOption = uiState.weightUnit,
                    onOptionSelected = { viewModel.updateWeightUnit(it) } // Pass the update function
                )
                RadioButtonSettingItem(
                    label = "Muscle Unit",
                    options = listOf("cm", "in"),
                    selectedOption = uiState.muscleUnit,
                    onOptionSelected = { viewModel.updateMuscleUnit(it) }
                )
                RadioButtonSettingItem(
                    label = "Distance Unit",
                    options = listOf("km", "miles"),
                    selectedOption = uiState.distanceUnit,
                    onOptionSelected = { viewModel.updateDistanceUnit(it) }
                )
            }

        }
    }
}

@Composable
private fun SmartFeaturesSettingsPage(viewModel: SettingsViewModel, onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState

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
                RadioButtonSettingItem(
                    label = "Smart Weight & Reps",
                    options = listOf("On", "Off"),
                    selectedOption = uiState.smartFeatures,
                    onOptionSelected = { viewModel.updateSmartFeatures(it) }
                )
            }
        }
    }
}

@Composable
private fun SoundSettingsPage(viewModel: SettingsViewModel, onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState

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
                    selectedOption = uiState.soundBeforeStep,
                    onOptionSelected = { viewModel.updateSoundBeforeStep(it) }
                )
                RadioButtonSettingItem(
                    label = "Vibration Before Step",
                    options = listOf("5 sec", "0 sec", "off"),
                    selectedOption = uiState.vibrationBeforeStep,
                    onOptionSelected = { viewModel.updateVibrationBeforeStep(it) }
                )
            }
        }
    }
}

// <<< CHANGED: Refactored to use ViewModel
@Composable
private fun RemindersSettingsPage(viewModel: SettingsViewModel, onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState
    var showTimePickerForDay by remember { mutableStateOf<String?>(null) }

    // When a day is selected to edit time, this will trigger the dialog
    showTimePickerForDay?.let { day ->
        val initialTime = uiState.reminders.first { it.day == day }.time
        TimePickerDialog(
            initialTime = initialTime,
            onDismiss = { showTimePickerForDay = null },
            onConfirm = { newHour, newMinute ->
                val newTime24 = String.format("%02d:%02d", newHour, newMinute)
                viewModel.updateReminderTime(day, newTime24)
                showTimePickerForDay = null
            }
        )
    }

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
                    // Iterate over the list of reminders from the UiState
                    uiState.reminders.forEach { reminder ->
                        ReminderItem(
                            day = reminder.day,
                            time = reminder.time, // Pass time from state
                            isEnabled = reminder.isEnabled, // Pass enabled from state
                            onEnabledChange = { isEnabled ->
                                viewModel.updateReminderEnabled(reminder.day, isEnabled)
                            },
                            onTimeClick = {
                                showTimePickerForDay = reminder.day
                            }
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
    time: String, // Expects 24-hour "HH:mm" format
    isEnabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    onTimeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isEnabled,
            onCheckedChange = onEnabledChange, // Use the callback
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
            onClick = onTimeClick, // Use the callback
        ) {
            Text(
                // Format the 24-hour time from state into 12-hour AM/PM for display
                text = formatTime(time),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(
                    alpha = if (isEnabled) 1f else 0.5f
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


//@Composable
//private fun DisplaySettingsPage(onNavigateBack: () -> Unit) {
//    SettingsPageScaffold(title = "WORKOUT TAB DISPLAY", onNavigateBack = onNavigateBack) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            HorizontalDivider(thickness= 1.dp, color = MaterialTheme.colorScheme.outline)
//            Column(
//                modifier = Modifier.padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                RadioButtonSettingItem(label = "Default View", options = listOf("List", "Grid"))
//            }
//        }
//    }
//}

@Composable
private fun RadioButtonSettingItem(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
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
                        modifier = Modifier.selectable(
                            selected = (optionText == selectedOption),
                            onClick = { onOptionSelected(optionText) }, // Use the callback
                            role = Role.RadioButton
                        )
                    ) {
                        RadioButton(
                            selected = (optionText == selectedOption),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsPageScaffold(title: String, onNavigateBack: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
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
private fun ProfileDetailRow(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.background,
            onClick = onClick,
            modifier = Modifier.defaultMinSize(minWidth = 80.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun GenderPickerDialog(currentGender: String, onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var selectedGender by remember { mutableStateOf(currentGender) }
    val genders = listOf("Male", "Female")

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismiss,
        title = { Text("Select Gender") },
        text = {
            Column {
                genders.forEach { gender ->
                    Row(
                        Modifier.fillMaxWidth().selectable(
                            selected = (gender == selectedGender),
                            onClick = { selectedGender = gender },
                            role = Role.RadioButton
                        ).padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = (gender == selectedGender), onClick = null)
                        Text(text = gender, color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = { onConfirm(selectedGender) }) { Text("OK") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

@Composable
private fun ValuePickerDialog(
    title: String,
    unit: String,
    initialValue: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf(initialValue) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it.filter { char -> char.isDigit() || char == '.' } },
                label = { Text("Value") },
                suffix = { Text(unit) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        confirmButton = { TextButton(onClick = { onConfirm(text) }) { Text("OK") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    VitaHabitTheme {
        SettingsMenuScreen({}, {}, {}, {}, {})
    }
}