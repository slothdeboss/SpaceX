package com.slothdeboss.spacex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slothdeboss.spacex.data.db.converters.*
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.model.Rocket

@TypeConverters(
    LinksConverter::class,
    HeightConverter::class,
    DiameterConverter::class,
    MassConverter::class,
    PayloadWeightConverter::class,
    FirstStageConverter::class,
    SecondStageConverter::class,
    EnginesConverter::class,
    LandingLegsConverter::class
)
@Database(entities = [History::class, Rocket::class], version = 7, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao
    abstract fun getRocketsDao(): RocketsDao

}