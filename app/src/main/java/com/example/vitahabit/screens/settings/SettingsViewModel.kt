package com.example.vitahabit.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Calendar

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

    val workoutDisplayMode: String = "Expanded",

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

    fun updateWorkoutDisplayMode(newMode: String) {
        _uiState.value = _uiState.value.copy(workoutDisplayMode = newMode)
    }
}
