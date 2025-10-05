//package com.example.vitahabit.screens.exerciselist
//
//import android.app.Application
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.vitahabit.ViewModelFactory
//import com.example.vitahabit.ui.theme.VitaHabitTheme
//
//@Composable
//fun ExerciseListScreen(
//    viewModel: ExerciseListViewModel = viewModel(
//        factory = ViewModelFactory(LocalContext.current.applicationContext as Application)
//    ), // Get the ViewModel
//    onCloseClick: () -> Unit,
//    onExerciseClick: (ExerciseUiModel) -> Unit
//) {
//    val exercises by viewModel.exercises.collectAsState()
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column {
//            // Top Bar
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 8.dp, vertical = 8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TextButton(onClick = onCloseClick) {
//                    Text("Close")
//                }
//                Text(
//                    text = "Exercise List",
//                    style = MaterialTheme.typography.titleLarge
//                )
//                TextButton(onClick = { /* TODO: Add customize logic */ }) {
//                    Text("Customize")
//                }
//            }
//            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
//
//            // The list of exercises from the ViewModel
//            ExerciseList(
//                exercises = exercises,
//                onExerciseClick = onExerciseClick
//            )
//        }
//    }
//}
//
//@Composable
//private fun ExerciseList(
//    exercises: List<ExerciseUiModel>,
//    onExerciseClick: (ExerciseUiModel) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        modifier = modifier,
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(exercises) { exercise ->
//            ExerciseItem(
//                exercise = exercise,
//                onExerciseClick = onExerciseClick
//            )
//        }
//    }
//}
//
//@Composable
//private fun ExerciseItem(
//    exercise: ExerciseUiModel,
//    onExerciseClick: (ExerciseUiModel) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .clickable { onExerciseClick(exercise) },
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.secondary
//        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = exercise.imageId),
//                contentDescription = exercise.name,
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(MaterialTheme.shapes.small),
//                contentScale = ContentScale.Crop
//            )
//            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//                Text(
//                    text = exercise.name,
//                    color = MaterialTheme.colorScheme.onSecondary,
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Text(
//                    text = exercise.description,
//                    color = MaterialTheme.colorScheme.onSecondary,
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//        }
//    }
//}

// in ExerciseListScreen.kt
package com.example.vitahabit.screens.exerciselist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vitahabit.data.model.Exercise
import com.example.vitahabit.data.model.ExercisesRepository
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun ExerciseListScreen(
    onCloseClick: () -> Unit,
    onExerciseClick: (Exercise) -> Unit
) {
    val exercises = ExercisesRepository.exercises
    val firstExercise = exercises.firstOrNull()
    val remainingExercises = exercises.drop(1)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = onCloseClick,
                    modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("Close")
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier.padding(top = 32.dp, end = 0.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("Customize")
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 0.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline
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

            if (firstExercise != null) {
                Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)) {
                    ExerciseItem(
                        exercise = firstExercise,
                        onExerciseClick = onExerciseClick
                    )
                }
            }

            Text(
                text = "Next Exercise",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp, start = 14.dp)
            )

            ExerciseList(
                exercises = remainingExercises,
                onExerciseClick = onExerciseClick
            )
        }
    }
}

@Composable
fun ExerciseList(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise) -> Unit,
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
fun ExerciseItem(exercise: Exercise, onExerciseClick: (Exercise) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
            .clickable { onExerciseClick(exercise) },
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
                painter = painterResource(exercise.imageId),
                contentDescription = stringResource(exercise.nameId),
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(exercise.nameId),

                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(exercise.descriptionId),
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
        ExerciseListScreen(onCloseClick = {}, onExerciseClick = {})
    }
}