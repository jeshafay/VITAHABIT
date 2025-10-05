package com.example.vitahabit.screens.progress

// Imports for the classes that are now in their own files
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import android.content.Context
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.net.Uri
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme


// --- Data Classes and ViewModels ---

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
    val measurements: List<MeasurementData> = emptyList(),
    val achievements: List<AchievementUiModel> = emptyList()
)

class ProgressViewModel : ViewModel() {
    private val _uiState = mutableStateOf(ProgressUiState())
    val uiState: State<ProgressUiState> = _uiState

    init {
        loadProgressData()
    }

    private fun loadProgressData() {
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
            totalVolume = "32.9k",
            measurements = listOf(
                MeasurementData("BICEPS", 47.0f, bicepsColor),
                MeasurementData("ABS", 85.0f, absColor),
                MeasurementData("WAIST", 81.0f, waistColor),
                MeasurementData("CHEST", 84.2f, chestColor),
                MeasurementData("SHLDRS", 127.0f, shouldersColor),
                MeasurementData("THIGH", 68.5f, thighColor),
                MeasurementData("CALF", 53.0f, calfColor)
            ),
            achievements = listOf(
                AchievementUiModel("First Milestone", "10 workouts in a month", R.drawable.ic_launcher_foreground, true),
                AchievementUiModel("Rising Star", "10 workouts in a month", R.drawable.ic_launcher_foreground, true),
                AchievementUiModel("Power Month", "10 workouts in a month", R.drawable.ic_launcher_foreground, false),
            )
        )
    }
}


// --- Graph Data ---
private val yAxisLabels = listOf("150", "120", "90", "60", "30", "0")
private val xAxisLabels = listOf("05/09", "07/09", "09/09", "11/09")

// --- Main Screen Composable ---

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
        Column(modifier = Modifier.fillMaxSize()) {
            ProgressTopBar()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    StatisticsCard(
                        workoutCount = uiState.workoutCount,
                        totalHours = uiState.totalHours,
                        totalVolume = uiState.totalVolume
                    )
                }
                item {
                    AchievementsCard(
                        achievements = uiState.achievements,
                        onNavigateToAchievements = onNavigateToAchievements
                    )
                }
                item {
                    BeforeAndAfterCard(
                        beforeDate = "Jan 1, 2024",
                        beforeWeight = "80.0 kg",
                        afterDate = "Sep 29, 2025",
                        afterWeight = "75.0 kg"
                    )
                }
                item {
                    MeasurementsCard(
                        weight = uiState.weight,
                        measurements = uiState.measurements
                    )
                }
                item {
                    ExerciseGraphCard(
                        exerciseName = "Seated Dumbbell Overhead Press",
                        exerciseDetail = "Single Arm",
                        maxWeight = "120",
                        maxUnit = "kg"
                    )
                }
            }
        }
    }
}

// --- Top Bar Composable ---

@Composable
fun ProgressTopBar() {
    Spacer(modifier = Modifier.height(18.dp))
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Progress",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
    }
}

// --- Reusable Card Composables ---

@Composable
fun StatisticsCard(workoutCount: String, totalHours: String, totalVolume: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
fun AchievementsCard(
    achievements: List<AchievementUiModel>,
    onNavigateToAchievements: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "ACHIEVEMENTS",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
                TextButton(
                    onClick = onNavigateToAchievements,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text("More")
                }
            }
            Text(
                text = "Start training and achieve your goals",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 10.sp,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                achievements.take(3).forEach { achievement ->
                    AchievementIcon(achievement = achievement)
                }
            }
        }
    }
}

@Composable
fun BeforeAndAfterCard(
    beforeDate: String,
    beforeWeight: String,
    afterDate: String,
    afterWeight: String,
    photoViewModel: PhotoViewModel = viewModel()
) {
    val context = LocalContext.current
    var showDialogFor by remember { mutableStateOf<String?>(null) } // "before" or "after"
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    // --- Image Launchers ---
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                if (showDialogFor == "before") {
                    photoViewModel.beforeImageUri = it
                } else {
                    photoViewModel.afterImageUri = it
                }
            }
            // Clear the state AFTER the result is handled
            showDialogFor = null
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                tempCameraUri?.let {
                    if (showDialogFor == "before") {
                        photoViewModel.beforeImageUri = it
                    } else {
                        photoViewModel.afterImageUri = it
                    }
                }
            }
            // Clear the state AFTER the result is handled
            showDialogFor = null
        }
    )

    // --- Show Dialog Logic ---
    if (showDialogFor != null) {
        ImageSourceDialog(
            onDismissRequest = { showDialogFor = null },
            onCameraClick = {
                val newUri = createImageUri(context)
                tempCameraUri = newUri
                cameraLauncher.launch(newUri)
                // DO NOT clear the state here
            },
            onGalleryClick = {
                galleryLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                // DO NOT clear the state here
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "BEFORE AND AFTER",
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PhotoPlaceholder(
                    label = "Before",
                    date = beforeDate,
                    weight = beforeWeight,
                    imageUri = photoViewModel.beforeImageUri,
                    onClick = { showDialogFor = "before" }
                )
                Spacer(modifier = Modifier.width(16.dp))
                PhotoPlaceholder(
                    label = "After",
                    date = afterDate,
                    weight = afterWeight,
                    imageUri = photoViewModel.afterImageUri,
                    onClick = { showDialogFor = "after" }
                )
            }
        }
    }
}

    @Composable
    fun ImageSourceDialog(
        onDismissRequest: () -> Unit,
        onCameraClick: () -> Unit,
        onGalleryClick: () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            containerColor = MaterialTheme.colorScheme.surface,
            title = { Text("Choose Image", color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(vertical = 2.dp)) },
            text = {
                Column {
                    Text(
                        text = "Open Camera",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCameraClick() }
                            .padding(vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Open Gallery",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onGalleryClick() }
                            .padding(vertical = 2.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel")
                }
            }
        )
    }

fun createImageUri(context: Context): Uri {
    val imageFile = File.createTempFile(
        "camera_photo_${System.currentTimeMillis()}",
        ".jpg",
        File(context.cacheDir, "images")
    ).apply {
        parentFile?.mkdirs()
    }
    return FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "${context.packageName}.provider",
        imageFile
    )
}

@Composable
fun MeasurementsCard(weight: String, measurements: List<MeasurementData>) {
    val chartCeiling = 150.0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "kg",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }
            Text(
                text = "WEIGHT",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 2.dp)
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

@Composable
fun ExerciseGraphCard(
    exerciseName: String,
    exerciseDetail: String,
    maxWeight: String,
    maxUnit: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "EXERCISE GRAPHS",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Add exercises and set goals to see\nyour progress over time",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Track Exercise",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Track Exercise", style = MaterialTheme.typography.bodyMedium, color =  MaterialTheme.colorScheme.primary,)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = exerciseName, style = MaterialTheme.typography.bodyLarge)
                    Text(text = exerciseDetail, style = MaterialTheme.typography.bodySmall)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = maxWeight,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = maxUnit,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }
            ExerciseGraph(
                yAxisLabels = yAxisLabels,
                xAxisLabels = xAxisLabels
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* TODO */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_goal),
                        contentDescription = "Set Goal",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Set Goal", style = MaterialTheme.typography.bodyMedium, color =  MaterialTheme.colorScheme.primary,)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* TODO */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete Exercise",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Exercise", style = MaterialTheme.typography.bodyMedium, color =  MaterialTheme.colorScheme.primary,)
                }
            }
        }
    }
}


// --- Reusable Helper Composables ---

@Composable
fun ExerciseGraph(
    yAxisLabels: List<String>,
    xAxisLabels: List<String>,
    modifier: Modifier = Modifier
) {
    val dividerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
    val labelColor = MaterialTheme.colorScheme.onSurface
    val labelWidth = 30.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            yAxisLabels.forEach { label ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = labelColor,
                        modifier = Modifier.width(labelWidth),
                        textAlign = TextAlign.End
                    )
                    HorizontalDivider(
                        color = dividerColor,
                        thickness = 1.dp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = labelWidth + 8.dp), // Offset to align with the graph
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                xAxisLabels.forEach { label ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = labelColor
                    )
                }
            }
        }
    }
}

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
        Text(text = value.toString(), style = MaterialTheme.typography.labelSmall)
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(barHeight)
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                .background(color = MaterialTheme.colorScheme.background)
        )
        Text(
            text = "cm",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
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
            style = MaterialTheme.typography.titleLarge,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
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
        horizontalArrangement = Arrangement.SpaceAround,
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
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primary

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
@Composable
fun AchievementIcon(achievement: AchievementUiModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.alpha(if (achievement.isUnlocked) 1f else 0.4f)
    ) {
        Image(
            painter = painterResource(id = achievement.iconResId),
            contentDescription = achievement.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        )
        Text(
            text = achievement.name,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

// --- Preview Composable ---

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    VitaHabitTheme {
        ProgressScreen(onNavigateToAchievements = {})
    }
}