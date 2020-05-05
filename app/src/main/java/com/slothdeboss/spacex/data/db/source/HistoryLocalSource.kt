package com.slothdeboss.spacex.data.db.source

import com.slothdeboss.spacex.data.db.dao.HistoryDao
import com.slothdeboss.spacex.data.model.History

class HistoryLocalSource(
    private val dao: HistoryDao
): LocalSource<History> {

    override suspend fun insertData(data: List<History>) {
        dao.insertHistoryToDatabase(history = data)
    }

    override suspend fun obtainLocalData(): List<History> = dao.retrieveAllHistory()

    override suspend fun obtainDataById(id: Int): History = dao.retrieveHistoryById(id = id)

}