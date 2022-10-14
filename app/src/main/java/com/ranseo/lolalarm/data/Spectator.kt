package com.ranseo.lolalarm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Spectator(
    @field:Json(name = "gameId")
    val gameId:Long,
    @field:Json(name = "gameType")
    val gameType: String,
    @field:Json(name = "gameStartTime")
    val gameStartTime: Long,
    @field:Json(name = "gameLength")
    val gameLength:Long,
    @field:Json(name = "gameMode")
    val gameMode:String,
    @field:Json(name = "mapId")
    val mapId:Long,
    @field:Json(name = "participants")
    val participants: List<CurrentGameParticipant>
)  {
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
    )
}