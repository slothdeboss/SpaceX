package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.SecondStage

object SecondStageConverter {

    @JvmStatic
    @TypeConverter
    fun fromSecondStageToJson(value: SecondStage) : String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToSecondStage(value: String): SecondStage {
        val type = object: TypeToken<SecondStage>() {}.type
        return Gson().fromJson(value, type)
    }

}