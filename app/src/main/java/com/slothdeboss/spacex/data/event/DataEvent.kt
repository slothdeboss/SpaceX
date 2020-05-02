package com.slothdeboss.spacex.data.event

sealed class DataEvent

object LoadAllData: DataEvent()
data class LoadDataById(val id: Int): DataEvent()