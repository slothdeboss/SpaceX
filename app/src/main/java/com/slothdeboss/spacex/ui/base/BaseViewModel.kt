package com.slothdeboss.spacex.ui.base

import androidx.lifecycle.ViewModel
import com.slothdeboss.spacex.ui.event.DataEvent

abstract class BaseViewModel: ViewModel() {

    abstract fun render(event: DataEvent)

}