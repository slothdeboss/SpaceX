package com.slothdeboss.spacex.data.api.source

interface RemoteSource<T> {

    suspend fun getRemoteData(): List<T>

}