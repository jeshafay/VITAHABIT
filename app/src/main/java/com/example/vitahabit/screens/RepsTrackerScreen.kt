package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.vitahabit.data.model.Exercise
import com.example.vitahabit.data.model.ExercisesRepository.exercises
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun TrackerScreen(
    onCloseClick: () -> Unit,
) {

    // get the first exercise. The UI will handle the case where the list is empty.
    val currentExercise = exercises.firstOrNull()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // --- Top Bar ---
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onCloseClick) {
                    Text("EXIT", color = MaterialTheme.colorScheme.onSecondary)
                }
                Spacer(modifier = Modifier.width(70.dp))
                Text(
                    text = "Exercise ${exercises.indexOf(currentExercise)+1}/${exercises.size}",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            // --- Stats Card ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    StatColumn(title = "Time", value = "00:00")
                    StatColumn(title = "Volume", value = "0 kg")
                    StatColumn(title = "Reps", value = "${exercises[0].repsAmount}")
                }
            }

            // --- Main Content: Only show if there is an exercise ---
            if (currentExercise != null) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Image(
                        painter = painterResource(id = currentExercise.imageId),
                        contentDescription = stringResource(id = currentExercise.nameId),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Max Weight Lifted",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Column {
                        Text(
                            text = "70 kg",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "${exercises[0].repsAmount} reps",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 0.dp),
                        color = MaterialTheme.colorScheme.outline,
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Pass the non-null exercise to the RepList
                RepList(exercise = currentExercise)

            } else {
                // Show a message if there are no exercises
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Exercise Data Found")
                }
            }
        }
    }
}

// Helper composable for the stats card to reduce repetition
@Composable
fun StatColumn(title: String, value: String) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}


@Composable
fun RepList(exercise: Exercise, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = exercise.repsAmount) { setNumber ->
            RepListItem(setNumber = setNumber + 1)
        }
    }
}

@Composable
fun RepListItem(setNumber: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "70 kg",
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(16.dp)
            )
            VerticalDivider(
                modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background
            )
            Text(
                text = "10 reps",
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(16.dp)
            )
            VerticalDivider(
                modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background
            )
            Text(
                text = "Redo",
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerScreenPreview() {
    VitaHabitTheme {
        TrackerScreen(onCloseClick = {})
    }
}