package com.example.vitahabit.data

import com.example.vitahabit.data.local.ExerciseDao
import com.example.vitahabit.data.local.ExerciseEntity
import kotlinx.coroutines.flow.Flow

// The Repository takes the DAO as a parameter so it can access the database.
class ExercisesRepository(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): Flow<List<ExerciseEntity>> {
        return exerciseDao.getAllExercises()
    }
}
