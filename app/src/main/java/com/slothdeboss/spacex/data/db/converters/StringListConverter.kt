package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringListConverter {

    @JvmStatic
    @TypeConverter
    fun fromListToJson(value: List<String>): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

}