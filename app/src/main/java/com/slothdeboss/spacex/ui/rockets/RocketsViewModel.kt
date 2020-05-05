package com.slothdeboss.spacex.ui.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.ui.event.DataEvent
import com.slothdeboss.spacex.ui.event.LoadAllData
import com.slothdeboss.spacex.ui.event.LoadDataById
import com.slothdeboss.spacex.data.repository.RocketsRepository
import com.slothdeboss.spacex.data.state.*
import kotlinx.coroutines.launch
import kotlin.Exception

class RocketsViewModel(
    private val repository: RocketsRepository
) : ViewModel() {

    private val _state = MutableLiveData<DataState>()
    val state: LiveData<DataState>
        get() = _state

    fun render(event: DataEvent) {
        when (event) {
            LoadAllData -> fetchRockets()
            is LoadDataById -> fetchRocketById(id = event.id)
        }
    }

    private fun fetchRocketById(id: Int) {
        viewModelScope.launch {
            try{
                val data = repository.obtainDataById(id = id)
                _state.value = OnItemFetched(data = data)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = OnError
            }
        }
    }

    private fun fetchRockets() {
        _state.value = Loading
        viewModelScope.launch {
            try {
                val data = repository.obtainAllData()
                _state.value = OnListFetched(data = data)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = OnError
            }
        }
    }
}
