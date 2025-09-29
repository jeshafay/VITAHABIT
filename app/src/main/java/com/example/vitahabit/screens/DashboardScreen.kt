package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings

@Composable
fun DashboardScreen(onNavigateToExerciseList: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🔹 Title
            Text(
                text = "My Plan",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 22.sp // lebih kecil dari displayLarge
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 🔹 Subtitle
            Text(
                text = "Week 1/5 - Foundations",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "TODAY'S WORKOUT - Upper Body",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Stats
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                WorkoutStat("7 Exercises")
                WorkoutStat("58 Min")
                WorkoutStat("290 Cal")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Workout list
            val workoutList = listOf(
                "Push Up, Close Grip",
                "Push Up, Wide Grip",
                "Push Up, Diamond"
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(workoutList) { exercise ->
                    WorkoutCardWithImage(
                        title = exercise,
                        setsReps = "3 sets x 5 reps",
                        imageRes = R.drawable.push_up
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // 🔹 Button
            Button(
                onClick = { onNavigateToExerciseList() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp), // lebih kecil
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "START WORKOUT",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Bottom Navigation
            BottomNavigationBar()
        }
    }
}

@Composable
fun WorkoutStat(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun WorkoutCardWithImage(
    title: String,
    setsReps: String,
    imageRes: Int,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surface
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp), // lebih ramping
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), // lebih kecil dari 120dp
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = setsReps,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Filled.List, contentDescription = "Library") },
            label = { Text("Library", fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings", fontSize = 12.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    VitaHabitTheme {
        DashboardScreen(onNavigateToExerciseList = {})
    }
}
