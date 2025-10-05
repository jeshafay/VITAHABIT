package com.example.vitahabit.screens.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vitahabit.R // Assuming you have placeholder resources
import com.example.vitahabit.ui.theme.VitaHabitTheme

// UI model for a single achievement
data class AchievementUiModel(
    val name: String,
    val description: String,
    val iconResId: Int,
    val isUnlocked: Boolean
)

// The ViewModel for the AchievementsScreen
class AchievementsViewModel : ViewModel() {
    // In a real app, this data would come from your Room database
    val achievements: List<AchievementUiModel> = listOf(
        AchievementUiModel(
            "First Milestone",
            "Your first workout",
            R.drawable.ic_launcher_foreground,
            true
        ),
        AchievementUiModel(
            "Determination",
            "Completed 3 workouts",
            R.drawable.ic_launcher_foreground,
            true
        ),
        AchievementUiModel(
            "Gym Beast",
            "4 workouts in 1 week",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Rising Star",
            "Completed 7 workouts",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Power Month",
            "10 workouts in a month",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Gym Rat",
            "Total time at the gym = 24h",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "3 in a Row",
            "At least 1 weekly workout for 3 weeks",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "4 in a Row",
            "At least 1 weekly workout for 4 weeks",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "5 in a Row",
            "At least 1 weekly workout for 5 weeks",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Animal",
            "Total weight lifted = 250k kg",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Savage",
            "Total weight lifted = 500k kg",
            R.drawable.ic_launcher_foreground,
            false
        ),
        AchievementUiModel(
            "Hulk",
            "Total weight lifted = 1M kg",
            R.drawable.ic_launcher_foreground,
            false
        )
    )
}

@Composable
fun AchievementsScreen(
    onNavigateBack: () -> Unit,
    viewModel: AchievementsViewModel = viewModel()
) {
    val achievements = viewModel.achievements

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(2.dp))
            // --- Top Bar with Back Button ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            // --- Achievements Grid ---
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(achievements) { achievement ->
                    AchievementItem(achievement = achievement)
                }
            }
        }
    }
}

@Composable
fun AchievementItem(achievement: AchievementUiModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        // Use an alpha modifier to make locked achievements appear faded
        modifier = Modifier.alpha(if (achievement.isUnlocked) 1f else 0.4f)
    ) {
        Image(
            painter = painterResource(id = achievement.iconResId),
            contentDescription = achievement.name,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = achievement.name,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = achievement.description,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementsScreenPreview() {
    VitaHabitTheme {
        AchievementsScreen(onNavigateBack = {})
    }
}