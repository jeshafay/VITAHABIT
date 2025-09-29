package com.example.vitahabit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val nameResId: Int,
    val descriptionResId: Int,
    val iconResId: Int
)

@Entity(tableName = "user_achievements")
data class UserAchievementEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val achievementId: String,
    val dateUnlocked: Long
)