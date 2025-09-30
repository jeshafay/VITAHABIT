package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme


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
                    fontSize = 22.sp
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

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Days row
            DaysRow()

            Spacer(modifier = Modifier.height(16.dp))

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
                    .height(48.dp),
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
//            BottomNavigationBar()
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
    containerColor: Color = MaterialTheme.colorScheme.surface
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
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
fun DaysRow() {
    val days = listOf(
        "Sun" to "29",
        "Mon" to "30",
        "Tue" to "01",
        "Wed" to "02",
        "Thu" to "03",
        "Fri" to "04",
        "Sat" to "05"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(days) { (day, date) ->
            DayBox(day = day, date = date, isToday = day == "Mon")
        }
    }
}

@Composable
fun DayBox(day: String, date: String, isToday: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(48.dp)
            .background(
                color = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = day,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isToday) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = date,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (isToday) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
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