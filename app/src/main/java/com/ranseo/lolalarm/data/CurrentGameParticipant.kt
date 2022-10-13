package com.ranseo.lolalarm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentGameParticipant(
    @field:Json(name = "championId")
    val championId: Long,
    @field:Json(name = "profileIconId")
    val profileIconId : Long,
    @field:Json(name = "summonerName")
    val summonerName:String,
    @field:Json(name = "summonerId")
    val summonerId:String
) {

}
