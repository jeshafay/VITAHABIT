package com.example.vitahabit.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.AppRoutes
import com.example.vitahabit.R
import com.example.vitahabit.screens.progress.ProgressScreen
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun MainScreen(
//    navController: NavController,
    onNavigateToExerciseList: () -> Unit,
    onNavigateToAchievements: () -> Unit
) {
    val nestedNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    BottomNavItem.Dashboard,
                    BottomNavItem.Library,
                    BottomNavItem.Progress,
                    BottomNavItem.Settings
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(painter = painterResource(id = screen.iconResId), contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            nestedNavController.navigate(screen.route) {
                                popUpTo(nestedNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.tertiary,
                            selectedTextColor = MaterialTheme.colorScheme.tertiary,

                            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface,

                            // remove background/box
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = AppRoutes.DASHBOARD,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.DASHBOARD) {
                DashboardScreen(onNavigateToExerciseList = onNavigateToExerciseList)
            }
            composable(AppRoutes.LIBRARY) {
                LibraryScreen()
            }

            composable(AppRoutes.PROGRESS) {
                ProgressScreen(onNavigateToAchievements = onNavigateToAchievements)
            }
            composable(AppRoutes.SETTINGS) {
                SettingsScreen(onProfileClick = {}, onNotificationsClick = {})
            }
        }
    }
}

// It's best practice to move this to its own file (e.g., BottomNavItem.kt)
sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val iconResId: Int
) {
    object Dashboard : BottomNavItem(
        route = AppRoutes.DASHBOARD,
        title = "Workout",
        iconResId = R.drawable.dashboard_icon
    )
    object Library : BottomNavItem(
        route = AppRoutes.LIBRARY,
        title = "Library",
        iconResId = R.drawable.library_icon
    )
    object Progress : BottomNavItem(
        route = AppRoutes.PROGRESS,
        title = "Progress",
        iconResId = R.drawable.progression_icon
    )
    object Settings : BottomNavItem(
        route = AppRoutes.SETTINGS,
        title = "Settings",
        iconResId = R.drawable.settings_icon
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    VitaHabitTheme {
        MainScreen(onNavigateToExerciseList = {}, onNavigateToAchievements = {})
    }
}