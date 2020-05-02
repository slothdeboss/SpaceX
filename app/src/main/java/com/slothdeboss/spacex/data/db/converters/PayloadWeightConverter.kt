package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.PayloadWeight

object PayloadWeightConverter {

    @JvmStatic
    @TypeConverter
    fun fromPayloadToJson(value: List<PayloadWeight>?): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToPayload(value: String) : List<PayloadWeight>? {
        val type = object : TypeToken<List<PayloadWeight>> () {}.type
        return Gson().fromJson(value, type)
    }

}