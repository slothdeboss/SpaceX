package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.Engines

object EnginesConverter {

    @JvmStatic
    @TypeConverter
    fun fromEnginesToJson(value: Engines): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToEngines(value: String): Engines {
        val type = object: TypeToken<Engines>() {}.type
        return Gson().fromJson(value, type)
    }

}