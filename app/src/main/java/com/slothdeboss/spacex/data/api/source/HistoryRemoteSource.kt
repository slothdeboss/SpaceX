package com.slothdeboss.spacex.data.api.source

import com.slothdeboss.spacex.data.api.ApiService
import com.slothdeboss.spacex.data.model.History

class HistoryRemoteSource(private val api: ApiService): RemoteSource<History> {

    override suspend fun getRemoteData(): List<History> = api.getAllHistory()

}