package com.slothdeboss.spacex.ui.history

import com.slothdeboss.spacex.data.model.History

sealed class HistoryState

object Loading : HistoryState()
object OnError : HistoryState()
data class OnListFetched(val data: List<History>) : HistoryState()
data class OnItemFetched(val data: History): HistoryState()