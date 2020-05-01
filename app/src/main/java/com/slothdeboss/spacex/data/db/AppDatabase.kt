package com.slothdeboss.spacex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slothdeboss.spacex.data.db.converters.LinksConverter
import com.slothdeboss.spacex.data.model.History

@TypeConverters(LinksConverter::class)
@Database(entities = [History::class], version = 3, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao

}