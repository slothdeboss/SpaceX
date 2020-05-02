package com.slothdeboss.spacex.ui.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slothdeboss.spacex.data.model.Rocket
import com.slothdeboss.spacex.data.repository.RocketsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class RocketsViewModel(
    private val repository: RocketsRepository
) : ViewModel() {

    private val _rockets = MutableLiveData<List<Rocket>>()
    val rockets: LiveData<List<Rocket>>
        get() = _rockets

    fun fetchRockets() {
        viewModelScope.launch {
            try {
                _rockets.value = repository.obtainAllData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
