package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.source.MissionsRemoteSource
import com.slothdeboss.spacex.data.db.source.MissionsLocalSource
import com.slothdeboss.spacex.data.model.Mission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MissionsRepository(
    private val remoteSource: MissionsRemoteSource,
    private val localSource: MissionsLocalSource
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
        localSource.obtainDataById(id = id)
    }

    override suspend fun insertAllDataToLocal(data: List<Mission>) {
        try {
            withContext(Dispatchers.IO) {
                localSource.insertData(data = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<Mission> {
        val missions = withContext(Dispatchers.IO) {
            remoteSource.getRemoteData()
        }
        insertAllDataToLocal(data = missions)
        return missions
    }

    override suspend fun obtainAllLocalData(): List<Mission> = withContext(Dispatchers.IO) {
        localSource.obtainLocalData()
    }
}