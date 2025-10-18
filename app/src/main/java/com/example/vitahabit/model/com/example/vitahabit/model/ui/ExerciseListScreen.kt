package com.example.vitahabit.model.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vitahabit.model.Exercise
import com.example.vitahabit.model.ExercisesRepository

@Composable
fun ExerciseListScreen() {
    val exercises = ExercisesRepository.exercises

    LazyColumn(
        modifier = Modifier.Companion
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(exercises) { index, exercise ->
            ExerciseCard(exercise = exercise, index = index)
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, index: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.Companion.fillMaxWidth()
    ) {
        Row(modifier = Modifier.Companion.padding(12.dp)) {
            Image(
                painter = painterResource(id = exercise.imageId),
                contentDescription = stringResource(id = exercise.nameId),
                modifier = Modifier.Companion
                    .size(64.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.Companion.weight(1f)) {
                Text(
                    text = "${stringResource(id = exercise.nameId)} #${index + 1}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Companion.Bold)
                )
                Spacer(modifier = Modifier.Companion.height(4.dp))
                Text(
                    text = stringResource(id = exercise.descriptionId),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}