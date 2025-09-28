package com.example.vitahabit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val repsAmount: Int,
    val imageName: String, // Storing the name of the drawable, e.g., "placeholder_image"
    val videoUrl: String
)