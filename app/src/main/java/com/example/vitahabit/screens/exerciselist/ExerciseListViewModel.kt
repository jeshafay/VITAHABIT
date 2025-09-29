package com.example.vitahabit.screens.exerciselist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitahabit.data.ExercisesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExerciseListViewModel(
    application: Application,
    private val exercisesRepository: ExercisesRepository // It takes the real repository
) : AndroidViewModel(application) {

    val exercises: StateFlow<List<ExerciseUiModel>> =
        exercisesRepository.getAllExercises()
            .map { entityList ->
                entityList.map { entity ->
                    // The lookup happens safely in the ViewModel
                    val imageId = getApplication<Application>().resources.getIdentifier(
                        entity.imageName, "drawable", getApplication<Application>().packageName
                    )
                    // The entity properties now match
                    ExerciseUiModel(
                        id = entity.id,
                        name = entity.name,
                        description = entity.description,
                        imageId = imageId
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}