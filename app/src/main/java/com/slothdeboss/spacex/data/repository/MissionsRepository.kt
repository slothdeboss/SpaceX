package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.MissionsRemoteSource
import com.slothdeboss.spacex.data.db.MissionsDao
import com.slothdeboss.spacex.data.model.Mission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MissionsRepository(
    private val remoteSource: MissionsRemoteSource,
    private val dao: MissionsDao
): Repository<Mission> {

    override suspend fun obtainAllData(): List<Mission> {
        val missions = obtainAllLocalData()
        return if (missions.isEmpty()) {
            obtainAllRemoteData()
        } else {
            missions
        }
    }

    override suspend fun obtainDataById(id: Int): Mission = withContext(Dispatchers.IO) {
        dao.obtainMissionById(id = id)
    }

    override suspend fun insertAllDataToLocal(data: List<Mission>) {
        try {
            withContext(Dispatchers.IO) {
                dao.insertAllMissionToDatabase(missions = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<Mission> {
        val missions = withContext(Dispatchers.IO) {
            remoteSource.getMissions()
        }
        insertAllDataToLocal(data = missions)
        return missions
    }

    override suspend fun obtainAllLocalData(): List<Mission> = withContext(Dispatchers.IO) {
        dao.obtainAllMissionsFromDatabase()
    }
}