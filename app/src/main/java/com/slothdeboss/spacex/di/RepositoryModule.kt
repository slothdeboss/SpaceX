package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.data.repository.HistoryRepository
import com.slothdeboss.spacex.data.repository.RocketsRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        HistoryRepository(get(), get())
    }

    factory {
        RocketsRepository(get(), get())
    }

}