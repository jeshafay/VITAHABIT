// in data/local/VitaHabitDatabase.kt
package com.example.vitahabit.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.vitahabit.data.local.AchievementDao
import com.example.vitahabit.data.local.AchievementEntity
import com.example.vitahabit.data.local.ExerciseDao
import com.example.vitahabit.data.local.ExerciseEntity
import com.example.vitahabit.data.local.UserAchievementEntity

// Add entities to the @Database annotation.
@Database(entities = [ExerciseEntity::class, AchievementEntity::class, UserAchievementEntity::class], version = 1)
abstract class VitaHabitDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    // abstract function to get your new AchievementDao.
    abstract fun achievementDao(): AchievementDao

    companion object {
        @Volatile
        private var INSTANCE: VitaHabitDatabase? = null

        fun getDatabase(context: Context): VitaHabitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VitaHabitDatabase::class.java,
                    "vitahabit_database"
                )
                    // In a real app, you would add a migration strategy here when you change the version.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
