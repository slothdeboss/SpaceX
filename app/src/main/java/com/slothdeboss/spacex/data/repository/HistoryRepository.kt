package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.HistoryRemoteSource
import com.slothdeboss.spacex.data.db.HistoryDao
import com.slothdeboss.spacex.data.model.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class HistoryRepository(
    private val remoteSource: HistoryRemoteSource,
    private val dao: HistoryDao
) : Repository<History> {

    override suspend fun obtainAllData(): List<History> {
        val history = obtainAllLocalData()
        return if (history.isEmpty()) {
            val remoteHistory = obtainAllRemoteData()
            remoteHistory
        } else {
            history
        }
    }

    override suspend fun obtainDataById(id: Int): History = withContext(Dispatchers.IO) {
        dao.retrieveHistoryById(id = id)
    }

    override suspend fun obtainAllLocalData(): List<History> = withContext(Dispatchers.IO) {
        dao.retrieveAllHistory()
    }

    override suspend fun insertAllDataToLocal(data: List<History>) {
        try {
            withContext(Dispatchers.IO) {
                dao.insertHistoryToDatabase(history = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<History> {
        val remoteHistory = withContext(Dispatchers.IO) {
            remoteSource.getHistory()
        }.map { history ->
            history.eventDate = createDateString(history.eventDate)
            history
        }
        insertAllDataToLocal(data = remoteHistory)
        return remoteHistory
    }

    private fun createDateString(date: String) : String {
        val eventDate = date.substring(0..9)
        return "Date: $eventDate"
    }
}