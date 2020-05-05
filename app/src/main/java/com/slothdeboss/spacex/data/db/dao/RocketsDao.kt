package com.slothdeboss.spacex.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slothdeboss.spacex.data.model.Rocket

@Dao
interface RocketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocketsToDatabase(rockets: List<Rocket>)

    @Query(value = "SELECT * FROM rocket")
    suspend fun getAllRocketsFromDatabase(): List<Rocket>

    @Query(value = "SELECT * FROM rocket WHERE id LIKE :id")
    suspend fun getRocketById(id: Int): Rocket
}