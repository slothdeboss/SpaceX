package com.slothdeboss.spacex.data.api

class MissionsRemoteSource(private val api: ApiService) {

    suspend fun getMissions() = api.getAllMissions()

}