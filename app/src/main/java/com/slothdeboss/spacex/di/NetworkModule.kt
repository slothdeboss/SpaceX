package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.data.api.HistoryRemoteSource
import com.slothdeboss.spacex.data.api.ApiService
import com.slothdeboss.spacex.data.api.RocketsRemoteSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.spacexdata.com/v3/"

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        HistoryRemoteSource(get())
    }

    single {
        RocketsRemoteSource(get())
    }
}