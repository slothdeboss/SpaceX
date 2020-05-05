package com.slothdeboss.spacex.data.api.source

import com.slothdeboss.spacex.data.api.ApiService
import com.slothdeboss.spacex.data.model.Rocket

class RocketsRemoteSource(private val api: ApiService): RemoteSource<Rocket> {

    override suspend fun getRemoteData(): List<Rocket> = api.getAllRockets()

}