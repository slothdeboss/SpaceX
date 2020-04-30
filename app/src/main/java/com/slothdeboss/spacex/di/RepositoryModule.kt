package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.repository.HistoryRepository
import com.slothdeboss.spacex.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {

    factory<Repository<History>> {
        HistoryRepository(get(), get())
    }
}