package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.FirstStage

object FirstStageConverter {

    @JvmStatic
    @TypeConverter
    fun fromFirstStageToJson(value: FirstStage): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToFirstStage(value: String): FirstStage {
        val type = object : TypeToken<FirstStage>() {}.type
        return Gson().fromJson(value, type)
    }

}