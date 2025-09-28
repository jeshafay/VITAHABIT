package com.example.vitahabit.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vitahabit.AppRoutes
//import com.example.vitahabit.BottomNavItem
import com.example.vitahabit.ui.screens.dashboard.DashboardScreen
import com.example.vitahabit.screens.ExerciseListScreen
import com.example.vitahabit.screens.SettingsScreen
import com.example.vitahabit.ui.theme.VitaHabitTheme

@Composable
fun MainScreen(onNavigateToExerciseList: () -> Unit) {
    val nestedNavController = rememberNavController()
//    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {

                val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    BottomNavItem.Dashboard,
                    BottomNavItem.ExerciseList,
//                    BottomNavItem.Progress,
                    BottomNavItem.Settings,
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            nestedNavController.navigate(screen.route) {

                                popUpTo(nestedNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        // Add this 'colors' parameter to control the icon and text colors
                        colors = NavigationBarItemDefaults.colors(
                            // Set both selected and unselected colors to onSurface
                            selectedIconColor = MaterialTheme.colorScheme.onSurface,
                            selectedTextColor = MaterialTheme.colorScheme.onSurface,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                            // Use a contrasting color for the indicator to show which item is active
                            indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // This is the nested NavHost that swaps the content
//        NavHost(
//            navController = navController,
//            startDestination = BottomNavItem.Dashboard.route,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(BottomNavItem.Dashboard.route) { DashboardScreen(onNavigateToExerciseList = { navController.navigate(BottomNavItem.ExerciseList.route) }) }
//            composable(BottomNavItem.ExerciseList.route) { ExerciseListScreen(onCloseClick = { navController.popBackStack() }, onExerciseClick = {}) }
//            composable(BottomNavItem.Settings.route) { SettingsScreen(onProfileClick = {}, onNotificationsClick = {}) }
//        }

        NavHost(
            navController = nestedNavController,
            startDestination = AppRoutes.DASHBOARD,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.DASHBOARD) {
                // The action is then passed down to the DashboardScreen where the button is
                DashboardScreen(onNavigateToExerciseList = onNavigateToExerciseList)
            }
            composable(AppRoutes.SETTINGS) {
                SettingsScreen(onProfileClick = {}, onNotificationsClick = {})
            }
        }
    }
}
sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dashboard : BottomNavItem(
        route = "dashboard",
        title = "Dashboard",
        icon = Icons.Outlined.Person
    )
    object ExerciseList : BottomNavItem(
        route = "exercise_list",
        title = "Workout",
        icon = Icons.Outlined.Person
    )
    object Settings : BottomNavItem(
        route = "settings",
        title = "Settings",
        icon = Icons.Outlined.Person
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    VitaHabitTheme {
        MainScreen(onNavigateToExerciseList = {  })
    }
}
