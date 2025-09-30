package com.example.vitahabit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.screens.DashboardScreen
import com.example.vitahabit.screens.exerciselist.ExerciseListScreen
import com.example.vitahabit.screens.progress.AchievementsScreen
import com.example.vitahabit.screens.TrackerScreen
import com.example.vitahabit.screens.LoginScreen
import com.example.vitahabit.screens.*
import com.example.vitahabit.ui.theme.VitaHabitTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitaHabitTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val startState by viewModel.startState.collectAsState()
                    AppNavigation(startState = startState)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(startState: AppStartState) {
    val navController = rememberNavController()
    val startDestination = when (startState) {
        is AppStartState.LoggedIn -> AppRoutes.MAIN_APP
        is AppStartState.LoggedOut -> AppRoutes.LOGIN
        is AppStartState.Loading -> "loading"
    }

    if (startState is AppStartState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            // Set all animations to None (null) for instant transitions
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(AppRoutes.LOGIN) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate(AppRoutes.MAIN_APP) {
                            popUpTo(AppRoutes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }
            composable(AppRoutes.MAIN_APP) {
                MainScreen(
                    onNavigateToExerciseList = { navController.navigate(AppRoutes.EXERCISE_LIST) },
                    onNavigateToAchievements = { navController.navigate(AppRoutes.ACHIEVEMENTS) }
                )
            }
            composable(AppRoutes.EXERCISE_LIST) {
                ExerciseListScreen(
                    onCloseClick = { navController.popBackStack() }, // Using popBackStack is correct here
                    onExerciseClick = { navController.navigate(AppRoutes.REPS_TRACKER) }
                )
            }
            composable(AppRoutes.REPS_TRACKER) {
                TrackerScreen(
                    onCloseClick = { navController.popBackStack() }
                )
            }
            composable(AppRoutes.ACHIEVEMENTS) {
                AchievementsScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}