package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.data.model.Exercise
import com.example.vitahabit.data.model.ExercisesRepository.exercises
import com.example.vitahabit.ui.theme.VitaHabitTheme

//enum class SetState {
//    COMPLETED, SUGGESTED, UPCOMING
//}


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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // --- Top Bar (No Changes Here) ---
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onCloseClick) {
                    Text("EXIT", color = MaterialTheme.colorScheme.onSecondary)
                }
                val exerciseText = if (currentExercise != null) {
                    "Exercise ${exercises.indexOf(currentExercise) + 1}/${exercises.size}"
                } else {
                    "No Exercise"
                }
                Text(
                    text = exerciseText,
                    color = Color(0xFFD9D9D9),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.width(60.dp))
            }

            // --- Stats Card (No Changes Here) ---
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
                    StatColumn(title = "Reps", value = "${currentExercise?.repsAmount ?: 0}")
                }
            }

            // --- Main Content (No Changes Here) ---
            if (currentExercise != null) {
                Column(modifier = Modifier.weight(1f)) {
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
                            color = Color(0xFFD9D9D9),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Column {
                            Text(
                                text = "70 kg",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "${currentExercise.repsAmount} reps",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "Today",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outline,
                            thickness = 1.dp
                        )
                        Spacer(modifier = Modifier.height(13.dp))
                    }
                    RepList(exercise = currentExercise)
                }
            } else {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Exercise Data Found")
                }
            }

            // --- Bottom Buttons Section (FIXED) ---
            Column(
                // FIX: Use Alignment.Start to align children to the left (start) of the column.
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // This button is centered because the parent column's alignment is overridden here.
                Card(
                    onClick = { /* TODO: Handle log all sets click */ },
                    modifier = Modifier
                        .width(180.dp)
                        .align(Alignment.CenterHorizontally), // Center this specific item
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD9D9D9)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Log All Sets",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Customize Exercise",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 15.sp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Next Exercise",
                    softWrap = false, // to keep the text on a single line.
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 15.sp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    onClick = { /* TODO: Handle next exercise click */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF6A6A6A)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.trizepsdr_cken_am_kabelzug_und_mit_dem_theraband___modusx_1),
                            contentDescription = "placeholder",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(MaterialTheme.shapes.small),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Overhead Tricep Extensions",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "3 sets x 10-12 reps x 20kg",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
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
fun ValueWithUnitText(
    value: String,
    unit: String,
    modifier: Modifier = Modifier
) {
    // buildAnnotatedString allows different styles within the same text element
    val annotatedString = buildAnnotatedString {
        // Style for the main value (e.g., "70")
        withStyle(style = SpanStyle(fontSize = 20.sp)) {
            append(value)
        }
        // Add a space between the value and the unit
        append(" ")
        // Style for the unit (e.g., "kg")
        withStyle(style = SpanStyle(fontSize = 14.sp)) {
            append(unit)
        }
    }
    Text(
        text = annotatedString,
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    )
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
            modifier = Modifier
                .padding(vertical = 2.dp) // Apply vertical padding to the row
                .fillMaxWidth()
        ) {
            // Box for the weight section
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ValueWithUnitText(value = "70", unit = "kg")
            }

            // A decorative divider
            VerticalDivider(
                modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
            )

            // Box for the reps section
            Box(
                modifier = Modifier.weight(1f), // Takes up 1 part of the available space
                contentAlignment = Alignment.Center
            ) {
                ValueWithUnitText(value = "10", unit = "reps")
            }

            VerticalDivider(
                modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
            )

            // Box for the "Redo" and checkmark section
            Box(
                modifier = Modifier.weight(1f), // Takes up 1 part of the available space
                contentAlignment = Alignment.Center
            ) {
                // Use a Column to place the icon above the text
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed Set",
                        tint = MaterialTheme.colorScheme.background
                    )
                    Text(
                        text = "Redo",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 14.sp
                    )
                }
            }
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