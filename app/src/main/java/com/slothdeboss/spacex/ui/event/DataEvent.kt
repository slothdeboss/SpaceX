package com.slothdeboss.spacex.ui.event

sealed class DataEvent

object LoadAllData: DataEvent()
data class LoadDataById(val id: Int): DataEvent()