package com.slothdeboss.spacex.ui.history.state

sealed class HistoryEvent

object LoadAllHistory: HistoryEvent()
data class LoadHistoryById(val id: Int): HistoryEvent()