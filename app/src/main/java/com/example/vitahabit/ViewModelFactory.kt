package com.example.vitahabit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vitahabit.data.ExercisesRepository
import com.example.vitahabit.data.local.VitaHabitDatabase
import com.example.vitahabit.screens.exerciselist.ExerciseListViewModel

// This factory knows how to create all the ViewModels in our app that have dependencies.
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseListViewModel::class.java)) {
            // If the UI asks for an ExerciseListViewModel...
            // 1. Get the database DAO.
            val exerciseDao = VitaHabitDatabase.getDatabase(application).exerciseDao()
            // 2. Create the repository with the DAO.
            val repository = ExercisesRepository(exerciseDao)
            // 3. Create the ViewModel with the repository.
            @Suppress("UNCHECKED_CAST")
            return ExerciseListViewModel(application, repository) as T
        }
        // You can add more 'if' blocks here for your other ViewModels
        // (e.g., ProgressViewModel) when they need dependencies.
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}