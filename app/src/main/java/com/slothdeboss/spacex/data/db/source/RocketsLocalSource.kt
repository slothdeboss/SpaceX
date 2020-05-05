package com.slothdeboss.spacex.data.db.source

import com.slothdeboss.spacex.data.db.dao.RocketsDao
import com.slothdeboss.spacex.data.model.Rocket

class RocketsLocalSource(
    private val dao: RocketsDao
): LocalSource<Rocket> {

    override suspend fun insertData(data: List<Rocket>) {
        dao.insertRocketsToDatabase(rockets = data)
    }

    override suspend fun obtainLocalData(): List<Rocket> = dao.getAllRocketsFromDatabase()

    override suspend fun obtainDataById(id: Int): Rocket = dao.getRocketById(id = id)

}