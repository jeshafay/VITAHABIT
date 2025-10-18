package com.example.vitahabit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseID: Int = 0,
    val nama: String,
    val namaFileGambar: String,
    val urlVideo: String,
    val grupOtotUtama: String,
    val peralatan: String,
    val tipeLatihan: String
)
