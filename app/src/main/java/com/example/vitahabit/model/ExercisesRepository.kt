package com.example.vitahabit.model

import com.example.vitahabit.R

object ExercisesRepository {

    private val baseExercises = listOf(
        Exercise(R.string.exercise1, R.string.exercise_description1, R.drawable.trizepsdr_cken_am_kabelzug_und_mit_dem_theraband___modusx_1),
        Exercise(R.string.exercise2, R.string.exercise_description2, R.drawable.push_up),
        Exercise(R.string.exercise3, R.string.exercise_description3, R.drawable.trizepsdr_cken_am_kabelzug_und_mit_dem_theraband___modusx_1),
        Exercise(R.string.exercise4, R.string.exercise_description4, R.drawable.push_up),
        Exercise(R.string.exercise5, R.string.exercise_description5, R.drawable.trizepsdr_cken_am_kabelzug_und_mit_dem_theraband___modusx_1),
        Exercise(R.string.exercise6, R.string.exercise_description6, R.drawable.push_up)
    )

    // 🔁 Loop 500x dengan variasi otomatis
    val exercises = List(500) { index ->
        val base = baseExercises[index % baseExercises.size]
        base.copy(
            // misalnya tambahkan index biar kelihatan unik
            nameId = base.nameId,
            descriptionId = base.descriptionId
        )
    }
}
