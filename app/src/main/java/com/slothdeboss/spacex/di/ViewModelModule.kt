package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.ui.history.HistoryViewModel
import com.slothdeboss.spacex.ui.launches.LaunchesViewModel
import com.slothdeboss.spacex.ui.missions.MissionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HistoryViewModel(get()) }
    viewModel { LaunchesViewModel() }
    viewModel { MissionsViewModel() }
}