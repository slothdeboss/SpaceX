package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.Mass

object MassConverter {

    @JvmStatic
    @TypeConverter
    fun fromMassToJson(value: Mass) : String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToMass(value: String) : Mass {
        val type = object : TypeToken<Mass> () {}.type
        return Gson().fromJson(value, type)
    }

}