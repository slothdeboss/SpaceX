package com.slothdeboss.spacex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Rocket(
    @PrimaryKey
    val id: Int,
    val active: Boolean,
    val stages: String,
    val boosters: String,
    @SerializedName(value = "cost_per_launch")
    @ColumnInfo(name = "cost_per_launch")
    val costPerLaunch: String,
    @SerializedName(value = "success_rate_pct")
    @ColumnInfo(name = "success_rate_pct")
    val successRate: String,
    val Country: String?,
    val company: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    @SerializedName(value = "payload_weight")
    val payloadWeight: List<PayloadWeight>?,
    @SerializedName(value = "first_stage")
    val firstStage: FirstStage,
    @SerializedName(value = "second_stage")
    val secondStage: SecondStage,
    val engines: Engines,
    @SerializedName(value = "landing_legs")
    val legs: LandingLegs,
    val wikipedia: String,
    val descriptions: String?,
    @SerializedName(value = "rocket_id")
    val rocketId: String,
    @SerializedName(value = "rocket_name")
    val rocketName: String,
    @SerializedName(value = "rocket_type")
    val rocketType: String
)

data class Height(
    val meters: String,
    val feet: String
)

data class Diameter(
    val meters: String,
    val feet: String
)

data class Mass(
    val kg: String,
    val lb: String
)

data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: String,
    val lb: String
)

data class FirstStage(
    val reusable: Boolean,
    val engines: String,
    @SerializedName(value = "fuel_amount_tons")
    val fuelAmount: String,
    @SerializedName(value = "burn_time_sec")
    val burnTimeSec: String
)

data class SecondStage(
    val engines: String,
    @SerializedName(value = "fuel_amount_tons")
    val fuelAmount: String,
    @SerializedName(value = "burn_time_sec")
    val burnTimeSec: String
)

data class Engines(
    val number: String,
    val type: String,
    val version: String?,
    val layout: String?,
    @SerializedName(value = "engine_loss_max")
    val engineLossMax: String?,
    @SerializedName(value = "propellant_1")
    val propellantFirst: String,
    @SerializedName(value = "propellant_2")
    val propellantSecond: String
)

data class LandingLegs(
    val number: String,
    val material: String
)