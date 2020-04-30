package com.slothdeboss.spacex.data.repository

interface Repository<T> {

    suspend fun getAllData(): List<T>
    suspend fun getDataById(id: Int): T

}