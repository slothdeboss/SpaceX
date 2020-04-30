package com.slothdeboss.spacex.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.repository.Repository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewModel(
    private val repository: Repository<History>
) : ViewModel() {

    private val _allHistory = MutableLiveData<List<History>>()
    val allHistory: LiveData<List<History>>
        get() = _allHistory

    private val _history = MutableLiveData<History>()
    val history: LiveData<History>
        get() = _history

    fun fetchHistory() {
        viewModelScope.launch {
            try {
                _allHistory.value = repository.getAllData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchHistoryById(id: Int) {
        viewModelScope.launch {
            try {
                _history.value = repository.getDataById(id = id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
