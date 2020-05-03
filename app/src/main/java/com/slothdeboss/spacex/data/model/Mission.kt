package com.slothdeboss.spacex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Mission(
    @SerializedName(value = "mission_name")
    val name: String,
    @SerializedName(value = "mission_id")
    @ColumnInfo(name = "mission_id")
    val missionId: String,
    val manufacturers: List<String>,
    @SerializedName(value = "payload_ids")
    val payloads: List<String>,
    val wikipedia: String,
    val website: String?,
    val twitter: String?,
    val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
