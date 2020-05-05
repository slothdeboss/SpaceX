package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.source.HistoryRemoteSource
import com.slothdeboss.spacex.data.db.source.HistoryLocalSource
import com.slothdeboss.spacex.data.model.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryRepository(
    private val remoteSource: HistoryRemoteSource,
    private val localSource: HistoryLocalSource
) : Repository<History> {

    override suspend fun obtainAllData(): List<History> {
        val history = obtainAllLocalData()
        return if (history.isEmpty()) {
            obtainAllRemoteData()
        } else {
            history
        }
    }

    override suspend fun obtainDataById(id: Int): History = withContext(Dispatchers.IO) {
        localSource.obtainDataById(id = id)
    }

    override suspend fun obtainAllLocalData(): List<History> = withContext(Dispatchers.IO) {
        localSource.obtainLocalData()
    }

    override suspend fun insertAllDataToLocal(data: List<History>) {
        try {
            withContext(Dispatchers.IO) {
                localSource.insertData(data = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<History> {
        val remoteHistory = withContext(Dispatchers.IO) {
            remoteSource.getRemoteData()
        }.map { history ->
            history.eventDate = createDateString(history.eventDate)
            history.flightNumber = createFlightString(history.flightNumber)
            history
        }
        insertAllDataToLocal(data = remoteHistory)
        return remoteHistory
    }

    private fun createDateString(date: String) : String {
        val eventDate = date.substring(0..9)
        return "Date: $eventDate"
    }

    private fun createFlightString(flight: String?): String? {
        return if (flight != null) {
            "Flight: $flight"
        } else {
            "Flight: unknown"
        }
    }
}