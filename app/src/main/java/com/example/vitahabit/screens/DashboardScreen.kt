package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme


@Composable
fun DashboardScreen(onNavigateToExerciseList: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // --- Top Bar ---
            TopBar()

            // --- Main Content ---
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // --- Filter Chips ---
                item {
                    FilterChipsRow()
                }

                // --- Days Row ---
                item {
                    DaysRow()
                }

                // --- Workout Title ---
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Text("Week 1/5 - Foundations", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimary)
                        Text("TODAY'S WORKOUT", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)
                        Text("Upper Body", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                    }
                }

                // --- Main Content Card (This is the fix) ---
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Stats are now inside the Card
                            StatsRow()

                            // Workout list is also inside the Card
                            val workoutList = listOf(
                                "Push Up, Close Grip",
                                "Push Up, Wide Grip",
                                "Push Up, Diamond"
                            )
                            // Use a regular Column and forEach since you can't nest a LazyColumn here
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                workoutList.forEach { exercise ->
                                    WorkoutCardWithImage(
                                        title = exercise,
                                        setsReps = "3 sets x 5 reps",
                                        imageRes = R.drawable.push_up
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // --- Start Workout Button ---
            Button(
                onClick = onNavigateToExerciseList,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "START WORKOUT",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

// --- Reusable Component Sections ---

@Composable
fun TopBar() {
    Spacer(modifier = Modifier.height(18.dp))
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Plan",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        //HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
        HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipsRow() {
    val filters = listOf("Muscles (16)", "45-60 Min", "Schedule", "Basic Exercises")
    var selectedChipIndex by remember { mutableStateOf(0) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(filters.size) { index ->
            FilterChip(
                selected = selectedChipIndex == index,
                onClick = { selectedChipIndex = index },
                label = { Text(filters[index]) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Transparent,
                    labelColor = MaterialTheme.colorScheme.onSurface,
                    iconColor = MaterialTheme.colorScheme.onSurface,
                    selectedContainerColor = MaterialTheme.colorScheme.surface,
                    selectedLabelColor = MaterialTheme.colorScheme.onSurface,
                    selectedTrailingIconColor = MaterialTheme.colorScheme.onSurface
                ),
//                border = FilterChipDefaults.filterChipBorder(
//                    borderColor = Color.Transparent,
//                    selectedBorderColor = Color.Transparent
//                )
            )
        }
    }
}

@Composable
fun DaysRow() {
    val daysAndDates = listOf(
        "Fr" to "05", "Sa" to "06", "Su" to "07", "Mo" to "08",
        "Tu" to "09", "We" to "10", "Th" to "11"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysAndDates.forEach { (day, date) ->
            DayBox(day = day, date = date, isToday = day == "Fr")
        }
    }
}

@Composable
fun DayBox(day: String, date: String, isToday: Boolean) {
    val backgroundColor = if (isToday) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isToday) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(text = day.uppercase(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
        Text(text = date, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WorkoutStat(icon = Icons.Default.Bolt, text = "7 Exercises")
        WorkoutStat(icon = Icons.Default.Timer, text = "58 Min")
        WorkoutStat(icon = Icons.Default.LocalFireDepartment, text = "290 Cal")
    }
}

@Composable
fun WorkoutStat(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun WorkoutCardWithImage(
    title: String,
    setsReps: String,
    imageRes: Int,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 400f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = setsReps,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    VitaHabitTheme {
        DashboardScreen(onNavigateToExerciseList = {})
    }
}