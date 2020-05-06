package com.slothdeboss.spacex.di

import com.slothdeboss.spacex.data.api.source.HistoryRemoteSource
import com.slothdeboss.spacex.data.api.ApiService
import com.slothdeboss.spacex.data.api.source.MissionsRemoteSource
import com.slothdeboss.spacex.data.api.source.RocketsRemoteSource
import org.koin.dsl.module
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(getProperty("BASE_URL", "https://api.spacexdata.com/v3/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<HistoryRemoteSource>()
    single<MissionsRemoteSource>()
    single<RocketsRemoteSource>()

}