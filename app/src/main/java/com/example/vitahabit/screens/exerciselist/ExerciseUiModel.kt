package com.example.vitahabit.screens.exerciselist

import androidx.annotation.DrawableRes

// A simple data class to hold the final, ready-to-display data for the UI.
data class ExerciseUiModel(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val imageId: Int
)
