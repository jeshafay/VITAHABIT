package com.example.vitahabit.ui.screens.exerciselist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vitahabit.model.Exercise
import com.example.vitahabit.model.ExercisesRepository
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun ExerciseListScreen(onCloseClick: () -> Unit) {
    val exercises = ExercisesRepository.exercises
    val firstExercise = exercises.firstOrNull()
    val remainingExercises = exercises.drop(1)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {

            // Tombol close
            TextButton(
                onClick = onCloseClick,
                modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Close")
            }

            Divider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Exercise List",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "Current Exercise",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 12.dp)
            )

            // Item pertama
            if (firstExercise != null) {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                    ExerciseItem(exercise = firstExercise)
                }
            }

            Text(
                text = "Next Exercise",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
            )

            // Sisa item
            ExerciseList(exercises = remainingExercises)
        }
    }
}

@Composable
fun ExerciseList(exercises: List<Exercise>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(exercises) { exercise ->
            ExerciseItem(exercise = exercise)
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = exercise.imageId),
                contentDescription = stringResource(id = exercise.nameId),
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = exercise.nameId),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = exercise.descriptionId),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    VitaHabitTheme {
        ExerciseListScreen(onCloseClick = {})
    }
}
