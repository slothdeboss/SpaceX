package com.slothdeboss.spacex.data.api

class RocketsRemoteSource(private val api: ApiService) {

    suspend fun getRockets() = api.getAllRockets()

}