package com.slothdeboss.spacex.di

import androidx.room.Room
import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                androidApplication().getString(R.string.app_database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().getHistoryDao()
    }

}