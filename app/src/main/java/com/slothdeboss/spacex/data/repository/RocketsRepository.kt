package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.source.RocketsRemoteSource
import com.slothdeboss.spacex.data.db.source.RocketsLocalSource
import com.slothdeboss.spacex.data.model.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RocketsRepository(
    private val remoteSource: RocketsRemoteSource,
    private val localSource: RocketsLocalSource
) : Repository<Rocket> {

    override suspend fun obtainAllData(): List<Rocket> {
        val rockets = obtainAllLocalData()
        return if (rockets.isEmpty()) {
            obtainAllRemoteData()
        } else {
            rockets
        }
    }

    override suspend fun obtainDataById(id: Int): Rocket = withContext(Dispatchers.IO) {
        localSource.obtainDataById(id = id)
    }

    override suspend fun insertAllDataToLocal(data: List<Rocket>) {
        try {
            withContext(Dispatchers.IO) {
                localSource.insertData(data = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<Rocket> {
        val rockets = withContext(Dispatchers.IO) {
            remoteSource.getRemoteData()
        }
        insertAllDataToLocal(data = rockets)
        return rockets
    }

    override suspend fun obtainAllLocalData(): List<Rocket> = withContext(Dispatchers.IO) {
        localSource.obtainLocalData()
    }

}