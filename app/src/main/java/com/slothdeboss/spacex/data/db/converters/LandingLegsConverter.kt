package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.LandingLegs

object LandingLegsConverter {

    @JvmStatic
    @TypeConverter
    fun fromLandingLegsToJson(value: LandingLegs) : String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToLandingLegs(value: String): LandingLegs {
        val type = object : TypeToken<LandingLegs>() {}.type
        return Gson().fromJson(value, type)
    }

}