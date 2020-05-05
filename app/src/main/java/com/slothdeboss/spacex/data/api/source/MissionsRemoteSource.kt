package com.slothdeboss.spacex.data.api.source

import com.slothdeboss.spacex.data.api.ApiService
import com.slothdeboss.spacex.data.model.Mission

class MissionsRemoteSource(private val api: ApiService): RemoteSource<Mission> {

    override suspend fun getRemoteData(): List<Mission> = api.getAllMissions()
}