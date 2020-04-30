package com.slothdeboss.spacex.data.api

class HistoryRemoteSource(private val api: ApiService) {

    suspend fun getHistory() = api.getAllHistory()

}