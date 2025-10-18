package com.example.vitahabit.model.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vitahabit.model.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise_table ORDER BY nama ASC")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)

    @Query("DELETE FROM exercise_table")
    suspend fun deleteAll()
}
