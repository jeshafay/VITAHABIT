package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitahabit.R // You'll need to add a push_up.png to your drawables
import com.example.vitahabit.ui.theme.VitaHabitTheme

// --- Data Models for the UI State ---
data class SavedRoutine(val id: String, val name: String)
data class ExerciseVideo(val id: String, val name: String, val imageRes: Int)

data class LibraryUiState(
    val savedRoutines: List<SavedRoutine> = emptyList(),
    val exerciseVideos: List<ExerciseVideo> = emptyList()
)

// --- ViewModel for the Library Screen ---
class LibraryViewModel : ViewModel() {
    private val _uiState = mutableStateOf(LibraryUiState())
    val uiState: State<LibraryUiState> = _uiState

    init {
        loadLibraryContent()
    }

    private fun loadLibraryContent() {
        // In a real app, this data would come from your Room database.
        _uiState.value = LibraryUiState(
            savedRoutines = listOf(
                SavedRoutine("1", "Full Body Strength"),
                SavedRoutine("2", "Morning Cardio"),
                SavedRoutine("3", "Leg Day Special")
            ),
            exerciseVideos = listOf(
                ExerciseVideo("1", "Push Up", R.drawable.push_up),
                ExerciseVideo("2", "Squat", R.drawable.push_up),
                ExerciseVideo("3", "Pull Up", R.drawable.push_up),
                ExerciseVideo("4", "Lunge", R.drawable.push_up),
                ExerciseVideo("5", "Plank", R.drawable.push_up),
                ExerciseVideo("6", "Deadlift", R.drawable.push_up)
            )
        )
    }
}


// --- Main UI for the Library Screen ---
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = viewModel()
) {
    val uiState by viewModel.uiState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Header Item ---
            item {
                Text(
                    text = "Library",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            // --- Filter Chips Section ---
            item {
                FilterChipsSection()
            }

            // --- "My Saved Routines" Section ---
            item {
                SavedRoutinesSection(routines = uiState.savedRoutines)
            }

            // --- "Exercise Encyclopedia" Section ---
            item {
                Text(
                    text = "Exercise Encyclopedia",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                ExerciseEncyclopediaSection(videos = uiState.exerciseVideos)
            }
        }
    }
}


// --- Reusable Section Composables ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChipsSection() {
    val filters = listOf("Muscles (16)", "Schedule", "Basic Exercises", "Equipment")
    var selectedChipIndex by remember { mutableStateOf(-1) } // -1 means no chip is selected

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters.size) { index ->
            FilterChip(
                selected = selectedChipIndex == index,
                onClick = { selectedChipIndex = if (selectedChipIndex == index) -1 else index },
                label = { Text(filters[index], color = MaterialTheme.colorScheme.onSurface) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    }
}

@Composable
private fun SavedRoutinesSection(routines: List<SavedRoutine>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "My Saved Routines",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(routines) { routine ->
                Card(
                    modifier = Modifier.size(width = 150.dp, height = 100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(routine.name, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
private fun ExerciseEncyclopediaSection(videos: List<ExerciseVideo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .height(900.dp)
            .padding(horizontal = 16.dp),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(videos) { video ->
            VideoThumbnailCard(video = video)
        }
    }
}

@Composable
private fun VideoThumbnailCard(video: ExerciseVideo) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = video.imageRes),
                contentDescription = video.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 200f
                        )
                    )
            )
            Icon(
                imageVector = Icons.Default.PlayCircle,
                contentDescription = "Play Video",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp)
            )
            Text(
                text = video.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    VitaHabitTheme {
        LibraryScreen()
    }
}