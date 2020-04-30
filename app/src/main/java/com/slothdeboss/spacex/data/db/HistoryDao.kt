package com.slothdeboss.spacex.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slothdeboss.spacex.data.model.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryToDatabase(history: List<History>)

    @Query("SELECT * FROM history")
    suspend fun retrieveAllHistory(): List<History>

    @Query("SELECT * FROM history WHERE id LIKE :id")
    suspend fun retrieveHistoryById(id: Int): History

}