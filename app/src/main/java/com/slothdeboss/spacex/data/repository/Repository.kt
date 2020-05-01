package com.slothdeboss.spacex.data.repository

interface Repository<T> {

    suspend fun obtainAllData(): List<T>
    suspend fun obtainDataById(id: Int): T
    suspend fun insertAllDataToLocal(data: List<T>)
    suspend fun obtainAllRemoteData(): List<T>
    suspend fun obtainAllLocalData(): List<T>

}