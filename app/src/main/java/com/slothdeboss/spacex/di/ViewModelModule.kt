package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.ui.history.HistoryViewModel
import com.slothdeboss.spacex.ui.rockets.RocketsViewModel
import com.slothdeboss.spacex.ui.missions.MissionsViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HistoryViewModel>()
    viewModel<RocketsViewModel>()
    viewModel<MissionsViewModel>()
}