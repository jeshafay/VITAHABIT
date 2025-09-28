package com.example.vitahabit.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.*
import androidx.compose.foundation.Image
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DashboardScreen(onNavigateToExerciseList: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = VitaHabitDarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🔹 Title
            Text(
                text = "My Plan",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = VitaHabitWhite
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 🔹 Subtitle
            Text(
                text = "Week 1/5 - Foundations",
                fontSize = 12.sp,
                color = VitaHabitOrange
            )
            Text(
                text = "TODAY'S WORKOUT - Upper Body",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = VitaHabitGrayWhite
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

            // 🔹 Workout list (sementara pakai gambar push_up semua biar tidak unresolved)
            val workoutList = listOf(
                "Push Up, Close Grip",
                "Push Up, Wide Grip",
                "Push Up, Diamond",
                "Sit Up",
                "Plank"
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(workoutList) { exercise ->
                    WorkoutCardWithImage(
                        title = exercise,
                        setsReps = "3 sets x 10 reps",
                        imageRes = R.drawable.push_up
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // 🔹 Button
            Button(
                onClick = { onNavigateToExerciseList() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VitaHabitOrange,
                    contentColor = VitaHabitDarkGray
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "START WORKOUT",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
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
        color = VitaHabitGrayWhite
    )
}

@Composable
fun WorkoutCardWithImage(
    title: String,
    setsReps: String,
    imageRes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = VitaHabitMediumGray),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VitaHabitDarkGray)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = VitaHabitWhite
                )
                Text(
                    text = setsReps,
                    fontSize = 12.sp,
                    color = VitaHabitGrayWhite
                )
            }
        }
    }
}

@Composable
fun DaysRow() {
    val today = LocalDate.now()
    val days = (0..6).map { offset ->
        val date = today.plusDays(offset.toLong())
        val dayShort = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) // Mon, Tue, ...
        val dayNumber = date.dayOfMonth.toString().padStart(2, '0')
        dayShort to dayNumber
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(days) { (day, date) ->
            DayBox(
                day = day,
                date = date,
                isToday = day == today.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            )
        }
    }
}

@Composable
fun DayBox(day: String, date: String, isToday: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .background(
                color = if (isToday) VitaHabitOrange else VitaHabitMediumGray,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = day,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isToday) VitaHabitDarkGray else VitaHabitGrayWhite
        )
        Text(
            text = date,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (isToday) VitaHabitDarkGray else VitaHabitWhite
        )
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = VitaHabitMediumGray) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Filled.List, contentDescription = "Library") },
            label = { Text("Library", fontSize = 12.sp, color = VitaHabitWhite) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings", fontSize = 12.sp, color = VitaHabitGrayWhite) }
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
