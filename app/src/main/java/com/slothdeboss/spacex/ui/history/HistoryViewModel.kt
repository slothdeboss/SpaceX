package com.slothdeboss.spacex.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.repository.Repository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: Repository<History>
) : ViewModel() {

    private val _historyState = MutableLiveData<HistoryState>()
    val historyState: LiveData<HistoryState>
        get() = _historyState

    fun render(event: HistoryEvent) {
        when (event) {
            LoadAllHistory -> fetchHistory()
            is LoadHistoryById -> fetchHistoryById(event.id)
        }
    }

    private fun fetchHistory() {
        _historyState.value = Loading
        viewModelScope.launch {
            try {
                val history = repository.getAllData()
                _historyState.value = OnListFetched(data = history)
            } catch (e: Exception) {
                e.printStackTrace()
                _historyState.value = OnError
            }
        }
    }

    private fun fetchHistoryById(id: Int) {
        viewModelScope.launch {
            try {
                val history = repository.getDataById(id = id)
                _historyState.value = OnItemFetched(data = history)
            } catch (e: Exception) {
                e.printStackTrace()
                _historyState.value = OnError
            }
        }
    }
}
