package com.slothdeboss.spacex.data.db.source

interface LocalSource<T> {

    suspend fun insertData(data: List<T>)
    suspend fun obtainLocalData(): List<T>
    suspend fun obtainDataById(id: Int): T

}