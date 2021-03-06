package com.slothdeboss.spacex.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.ui.event.DataEvent
import com.slothdeboss.spacex.ui.event.LoadAllData
import com.slothdeboss.spacex.ui.event.LoadDataById
import com.slothdeboss.spacex.data.repository.HistoryRepository
import com.slothdeboss.spacex.data.state.*
import com.slothdeboss.spacex.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: HistoryRepository
) : BaseViewModel() {

    private val _historyState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState>
        get() = _historyState

    override fun render(event: DataEvent) {
        when (event) {
            LoadAllData -> fetchHistory()
            is LoadDataById -> fetchHistoryById(event.id)
        }
    }

    private fun fetchHistory() {
        _historyState.value = Loading
        viewModelScope.launch {
            try {
                val history = repository.obtainAllData()
                _historyState.value =
                    OnListFetched(data = history)
            } catch (e: Exception) {
                e.printStackTrace()
                _historyState.value = OnError
            }
        }
    }

    private fun fetchHistoryById(id: Int) {
        viewModelScope.launch {
            try {
                val history = repository.obtainDataById(id = id)
                _historyState.value =
                    OnItemFetched(data = history)
            } catch (e: Exception) {
                e.printStackTrace()
                _historyState.value = OnError
            }
        }
    }
}
