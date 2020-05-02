package com.slothdeboss.spacex.data.api

import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.model.Rocket
import retrofit2.http.GET

interface ApiService {

    @GET(value = "history")
    suspend fun getAllHistory(): List<History>

    @GET(value = "rockets")
    suspend fun getAllRockets(): List<Rocket>

}