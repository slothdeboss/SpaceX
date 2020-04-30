package com.slothdeboss.spacex.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slothdeboss.spacex.data.model.Links

object LinksConverter {

    @JvmStatic
    @TypeConverter
    fun fromLinksToJson(value: Links): String {
        return Gson().toJson(value)
    }

    @JvmStatic
    @TypeConverter
    fun fromJsonToLinks(value: String?) : Links {
        val type = object : TypeToken<Links>() {}.type
        return Gson().fromJson(value, type)
    }

}