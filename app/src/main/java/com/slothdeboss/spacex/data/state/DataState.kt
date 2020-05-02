package com.slothdeboss.spacex.data.state

sealed class DataState

object Loading : DataState()
object OnError : DataState()
data class OnListFetched<T>(val data: T) : DataState()
data class OnItemFetched<T>(val data: T): DataState()