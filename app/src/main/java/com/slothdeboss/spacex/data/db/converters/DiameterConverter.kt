package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.Diameter

object DiameterConverter {

    @JvmStatic
    @TypeConverter
    fun fromDiameterToJson(value: Diameter) : String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToDiameter(value: String) : Diameter {
        val type = object : TypeToken<Diameter> () {}.type
        return Gson().fromJson(value, type)
    }

}