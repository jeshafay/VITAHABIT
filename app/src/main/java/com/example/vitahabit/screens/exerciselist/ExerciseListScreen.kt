package com.example.vitahabit.screens.exerciselist

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitahabit.ViewModelFactory
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun ExerciseListScreen(
    viewModel: ExerciseListViewModel = viewModel(
        factory = ViewModelFactory(LocalContext.current.applicationContext as Application)
    ), // Get the ViewModel
    onCloseClick: () -> Unit,
    onExerciseClick: (ExerciseUiModel) -> Unit
) {
    val exercises by viewModel.exercises.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onCloseClick) {
                    Text("Close")
                }
                Text(
                    text = "Exercise List",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = { /* TODO: Add customize logic */ }) {
                    Text("Customize")
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            // The list of exercises from the ViewModel
            ExerciseList(
                exercises = exercises,
                onExerciseClick = onExerciseClick
            )
        }
    }
}

@Composable
private fun ExerciseList(
    exercises: List<ExerciseUiModel>,
    onExerciseClick: (ExerciseUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(exercises) { exercise ->
            ExerciseItem(
                exercise = exercise,
                onExerciseClick = onExerciseClick
            )
        }
    }
}

@Composable
private fun ExerciseItem(
    exercise: ExerciseUiModel,
    onExerciseClick: (ExerciseUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onExerciseClick(exercise) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = exercise.imageId),
                contentDescription = exercise.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = exercise.name,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = exercise.description,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}