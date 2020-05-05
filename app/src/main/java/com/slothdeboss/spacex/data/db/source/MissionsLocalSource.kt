package com.slothdeboss.spacex.data.db.source

import com.slothdeboss.spacex.data.db.dao.MissionsDao
import com.slothdeboss.spacex.data.model.Mission
import kotlinx.coroutines.withContext

class MissionsLocalSource(
    private val dao: MissionsDao
): LocalSource<Mission> {

    override suspend fun insertData(data: List<Mission>) {
        dao.insertAllMissionToDatabase(missions = data)
    }

    override suspend fun obtainLocalData(): List<Mission> = dao.obtainAllMissionsFromDatabase()

    override suspend fun obtainDataById(id: Int): Mission = dao.obtainMissionById(id = id)

}