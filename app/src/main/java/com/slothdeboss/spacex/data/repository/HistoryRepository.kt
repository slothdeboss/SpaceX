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

    override suspend fun getAllData(): List<History> {
        val remoteHistory = withContext(Dispatchers.IO) {
            remoteSource.getHistory()
        }
        insertHistoryToDatabase(history = remoteHistory)
        return remoteHistory
    }

    override suspend fun getDataById(id: Int): History {
        return dao.retrieveHistoryById(id = id)
    }

    private suspend fun insertHistoryToDatabase(history: List<History>) {
        try {
            withContext(Dispatchers.IO) {
                dao.insertHistoryToDatabase(history = history)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}