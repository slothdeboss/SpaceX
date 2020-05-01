package com.slothdeboss.spacex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class History(
    @PrimaryKey
    val id: Int,
    val title: String,
    @SerializedName(value = "event_date_utc")
    @ColumnInfo(name = "event_date")
    var eventDate: String,
    @ColumnInfo(name = "flight_number")
    @SerializedName(value = "flight_number")
    val flightNumber: String?,
    val details: String?,
    val links: Links
)

data class Links(
    val reddit: String?,
    val article: String?,
    val wikipedia: String?
)