package com.example.vitahabit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Add the correct imports for your screens
import com.example.vitahabit.ui.screens.dashboard.DashboardScreen
import com.example.vitahabit.ui.screens.exerciselist.ExerciseListScreen
import com.example.vitahabit.ui.screens.login.LoginScreen
import com.example.vitahabit.ui.theme.VitaHabitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitaHabitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // The startDestination is now correctly set to the LOGIN screen
    NavHost(navController = navController, startDestination = AppRoutes.LOGIN) {
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(AppRoutes.DASHBOARD)
                }
            )
        }
        composable(AppRoutes.DASHBOARD) {
            DashboardScreen(
                onNavigateToExerciseList = {
                    navController.navigate(AppRoutes.EXERCISE_LIST)
                }
            )
        }
        composable(AppRoutes.EXERCISE_LIST) {
            ExerciseListScreen(
                onCloseClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}