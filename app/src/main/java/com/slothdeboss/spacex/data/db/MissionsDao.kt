package com.slothdeboss.spacex.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slothdeboss.spacex.data.model.Mission

@Dao
interface MissionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMissionToDatabase(missions: List<Mission>)

    @Query(value = "SELECT * FROM mission")
    suspend fun obtainAllMissionsFromDatabase(): List<Mission>

    @Query(value = "SELECT * FROM mission WHERE id Like :id")
    suspend fun obtainMissionById(id: Int): Mission

}