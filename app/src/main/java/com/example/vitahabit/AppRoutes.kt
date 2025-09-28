package com.example.vitahabit

object AppRoutes {
    // Routes for the root navigator in MainActivity
    const val LOGIN = "login"
    const val MAIN_APP = "main_app" // This will host the screens with the bottom bar

    // Routes for screens WITHIN the main app (nested navigator)
    const val DASHBOARD = "dashboard"
    const val SETTINGS = "settings"

    // Routes for full-screen destinations (root navigator)
    const val EXERCISE_LIST = "exercise_list"
    const val REPS_TRACKER = "reps_tracker"
}
