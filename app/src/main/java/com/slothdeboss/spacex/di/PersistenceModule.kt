package com.slothdeboss.spacex.di

import androidx.room.Room
import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.db.AppDatabase
import com.slothdeboss.spacex.data.db.source.HistoryLocalSource
import com.slothdeboss.spacex.data.db.source.MissionsLocalSource
import com.slothdeboss.spacex.data.db.source.RocketsLocalSource
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

    single {
        get<AppDatabase>().getRocketsDao()
    }

    single {
        get<AppDatabase>().getMissionsDao()
    }

    single {
        HistoryLocalSource(get())
    }

    single {
        MissionsLocalSource(get())
    }

    single {
        RocketsLocalSource(get())
    }
}