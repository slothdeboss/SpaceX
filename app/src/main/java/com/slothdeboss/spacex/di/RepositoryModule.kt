package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.data.repository.HistoryRepository
import com.slothdeboss.spacex.data.repository.MissionsRepository
import com.slothdeboss.spacex.data.repository.RocketsRepository
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val repositoryModule = module {

    factory<HistoryRepository>()
    factory<RocketsRepository>()
    factory<MissionsRepository>()

}