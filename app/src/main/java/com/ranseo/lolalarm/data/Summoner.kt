package com.ranseo.lolalarm.data

import androidx.room.Entity
import com.squareup.moshi.Json


@Entity(tableName = "summoner_table")
data class Summoner(
    @Json(name="id")
    val id: String,
    @Json(name="accountId")
    val accountId: String,
    @Json(name="puuid")
    val puuid: String,
    @Json(name="name")
    val name: String
) {
}