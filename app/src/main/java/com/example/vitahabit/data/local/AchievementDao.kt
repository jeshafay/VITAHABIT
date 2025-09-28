package com.example.vitahabit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements")
    fun getAllAchievements(): Flow<List<AchievementEntity>>

    @Query("SELECT achievementId FROM user_achievements WHERE userId = :userId")
    fun getUnlockedAchievementIdsForUser(userId: String): Flow<List<String>>

    @Insert
    suspend fun unlockAchievementForUser(userAchievement: UserAchievementEntity)
}