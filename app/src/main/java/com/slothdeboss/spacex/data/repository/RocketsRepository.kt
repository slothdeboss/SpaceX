package com.slothdeboss.spacex.data.repository

import com.slothdeboss.spacex.data.api.RocketsRemoteSource
import com.slothdeboss.spacex.data.db.RocketsDao
import com.slothdeboss.spacex.data.model.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RocketsRepository(
    private val rocketsRemoteSource: RocketsRemoteSource,
    private val dao: RocketsDao
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
        dao.getRocketById(id = id)
    }

    override suspend fun insertAllDataToLocal(data: List<Rocket>) {
        try {
            withContext(Dispatchers.IO) {
                dao.insertRocketsToDatabase(rockets = data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun obtainAllRemoteData(): List<Rocket> {
        val rockets = withContext(Dispatchers.IO) {
            rocketsRemoteSource.getRockets()
        }
        insertAllDataToLocal(data = rockets)
        return rockets
    }

    override suspend fun obtainAllLocalData(): List<Rocket> = withContext(Dispatchers.IO) {
        dao.getAllRocketsFromDatabase()
    }

}