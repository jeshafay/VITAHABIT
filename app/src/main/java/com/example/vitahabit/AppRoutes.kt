package com.example.vitahabit

object AppRoutes {
    // Routes for the root navigator in MainActivity
    const val START_LOGIN = "start_login"
    const val LOGIN = "login"
    const val QUESTIONS = "questions"
    const val MAIN_APP = "main_app" // This hosts the screens with the bottom bar

    // Routes for screens WITHIN the main app (nested navigator)
    const val DASHBOARD = "dashboard"
    const val SETTINGS = "settings"
    const val PROGRESS = "progress"
    const val LIBRARY = "library"

    // Routes for full-screen destinations (root navigator)
    const val EXERCISE_LIST = "exercise_list"
    const val REPS_TRACKER = "reps_tracker"
    const val ACHIEVEMENTS = "achievements"
}
