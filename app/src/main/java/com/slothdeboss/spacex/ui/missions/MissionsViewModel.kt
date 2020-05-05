package com.slothdeboss.spacex.ui.missions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.ui.event.DataEvent
import com.slothdeboss.spacex.ui.event.LoadAllData
import com.slothdeboss.spacex.ui.event.LoadDataById
import com.slothdeboss.spacex.data.repository.MissionsRepository
import com.slothdeboss.spacex.data.state.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MissionsViewModel(private val repository: MissionsRepository) : ViewModel() {

    private val _state = MutableLiveData<DataState>()
    val state: LiveData<DataState>
        get() = _state

    fun render(event: DataEvent) {
        when (event) {
            LoadAllData -> fetchAllData()
            is LoadDataById -> fetchMissionById(id = event.id)
        }
    }

    private fun fetchMissionById(id: Int) {
        viewModelScope.launch {
            try {
                val mission = repository.obtainDataById(id = id)
                _state.value = OnItemFetched(data = mission)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = OnError
            }
        }
    }

    private fun fetchAllData() {
        _state.value = Loading
        viewModelScope.launch {
            try {
                val missions = repository.obtainAllData()
                _state.value = OnListFetched(data = missions)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = OnError
            }
        }
    }

}
