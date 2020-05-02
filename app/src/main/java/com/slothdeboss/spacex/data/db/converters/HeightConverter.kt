package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.Height

object HeightConverter {

    @JvmStatic
    @TypeConverter
    fun fromHeightToJson(value: Height) : String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToHeight(value: String) : Height {
        val type = object : TypeToken<Height>() {}.type
        return Gson().fromJson(value, type)
    }

}