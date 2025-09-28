package com.example.vitahabit.screens.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitahabit.ui.theme.VitaHabitTheme

data class MeasurementData(
    val label: String,
    val value: Float,
    val color: Color
)

data class ProgressUiState(
    val weight: String = "0.0",
    val workoutCount: String = "0",
    val totalHours: String = "0.0",
    val totalVolume: String = "0",
    val measurements: List<MeasurementData> = emptyList()
)

class ProgressViewModel : ViewModel() {
    private val _uiState = mutableStateOf(ProgressUiState())
    val uiState: State<ProgressUiState> = _uiState

    init {
        loadProgressData()
    }

    private fun loadProgressData() {
        // In a real app, you would get this data from your Room database.
        // For now, we are simulating that by providing the data here.
        val bicepsColor = Color(0xFF42A5F5)
        val absColor = Color(0xFFEF5350)
        val waistColor = Color(0xFF66BB6A)
        val chestColor = Color(0xFFFFCA28)
        val shouldersColor = Color(0xFF9575CD)
        val thighColor = Color(0xFF26A69A)
        val calfColor = Color(0xFFEC407A)

        _uiState.value = ProgressUiState(
            weight = "75.0",
            workoutCount = "12",
            totalHours = "8.5",
            totalVolume = "1.2k",
            measurements = listOf(
                MeasurementData("BICEPS", 47.0f, bicepsColor),
                MeasurementData("ABS", 85.0f, absColor),
                MeasurementData("WAIST", 81.0f, waistColor),
                MeasurementData("CHEST", 84.2f, chestColor),
                MeasurementData("SHLDRS", 127.0f, shouldersColor),
                MeasurementData("THIGH", 68.5f, thighColor),
                MeasurementData("CALF", 53.0f, calfColor)
            )
        )
    }
}


// --- 3. UI: The screen now uses the ViewModel to get its data ---
@Composable
fun ProgressScreen(
    onNavigateToAchievements: () -> Unit,
    viewModel: ProgressViewModel = viewModel()
) {
    val uiState by viewModel.uiState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Text(
                    text = "Progress",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start = 28.dp, end = 28.dp, bottom = 8.dp)
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                StatisticsCard(
                    workoutCount = uiState.workoutCount,
                    totalHours = uiState.totalHours,
                    totalVolume = uiState.totalVolume
                )
            }
            item {
                AchievementsCard(onNavigateToAchievements = onNavigateToAchievements)
            }
            item { BeforeAndAfterCard() }
            item {
                MeasurementsCard(
                    weight = uiState.weight,
                    measurements = uiState.measurements
                )
            }
        }
    }
}

// --- Reusable Card Composables ---

@Composable
fun StatisticsCard(workoutCount: String, totalHours: String, totalVolume: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeframeSelector()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(title = "Number of\nWorkouts", value = workoutCount, modifier = Modifier.weight(1f))
                StatItem(title = "Hours at\nthe Gym", value = totalHours, modifier = Modifier.weight(1f))
                StatItem(title = "Total Weights\nLifted (kg)", value = totalVolume, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AchievementsCard(onNavigateToAchievements: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
                TextButton(
                    // Connect the "More" button's onClick to the action
                    onClick = onNavigateToAchievements,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text("More")
                }
            }
        }
    }
}

@Composable
fun BeforeAndAfterCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Before and After",
                style = MaterialTheme.typography.titleMedium,
            )
            // TODO: Add placeholder images or an upload button here
        }
    }
}

@Composable
fun MeasurementsCard(weight: String, measurements: List<MeasurementData>) {
    val chartCeiling = 150.0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "MEASUREMENTS",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = weight,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "kg",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }
            Text(
                "WEIGHT",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                measurements.forEach { measurement ->
                    MeasurementBar(
                        value = measurement.value,
                        label = measurement.label,
                        color = measurement.color,
                        chartCeiling = chartCeiling
                    )
                }
            }
        }
    }
}

// --- Reusable Helper Composables ---

@Composable
fun MeasurementBar(
    value: Float,
    label: String,
    color: Color,
    chartCeiling: Float,
    maxHeight: Dp = 120.dp
) {
    val barHeight = (value / chartCeiling).coerceAtMost(1f) * maxHeight
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = value.toString(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onBackground)
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(barHeight)
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                .background(MaterialTheme.colorScheme.background)
        )
        Text(text = "cm", style = MaterialTheme.typography.labelSmall)
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = color)
    }
}

@Composable
fun StatItem(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TimeframeSelector(modifier: Modifier = Modifier) {
    val timeframes = listOf("1W", "1M", "6M", "1Y", "All")
    var selectedTimeframe by remember { mutableStateOf(timeframes.first()) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        timeframes.forEach { timeframe ->
            SelectableChip(
                text = timeframe,
                isSelected = (selectedTimeframe == timeframe),
                onClick = {
                    selectedTimeframe = timeframe
                }
            )
        }
    }
}

@Composable
fun SelectableChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary // keep selected text color as background

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    VitaHabitTheme {
        // Update preview to pass the required parameter
        ProgressScreen(onNavigateToAchievements = {})
    }
}